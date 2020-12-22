package com.zjy.daydayup.LayoutManager;


import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

class FirstLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {

    private int mPendingPosition = RecyclerView.NO_POSITION;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }

    @Override
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
        int currentPosition = 0;
        int fixOffset = 0;

        //当childCount != 0时，证明是已经填充过View的，因为有回收
        //所以直接赋值为第一个child的position就可以
        if (getChildCount() != 0) {
            View childAt = getChildAt(0);
            if (childAt != null) {
                currentPosition = getPosition(childAt);
                fixOffset = getDecoratedLeft(childAt);
            }
        }
        if (mPendingPosition != RecyclerView.NO_POSITION) {
            currentPosition = mPendingPosition;
        }
        //轻量级的将view移除屏幕
        detachAndScrapAttachedViews(recycler);
        //开始填充view
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        //模仿LinearLayoutManager的写法，当可用距离足够和要填充
        //的itemView的position在合法范围内才填充View
        while (totalSpace > 0 && currentPosition < state.getItemCount()) {
            View view = recycler.getViewForPosition(currentPosition);
            addView(view);
            measureChild(view, 0, 0);

            right = left + getDecoratedMeasuredWidth(view);
            bottom = top + getDecoratedMeasuredHeight(view);
            layoutDecorated(view, left, top, right, bottom);

            currentPosition++;
            left += getDecoratedMeasuredWidth(view);
            //关键点
            totalSpace -= getDecoratedMeasuredWidth(view);
        }
        //layout完成后输出相关信息
        logChildCount("onLayoutChildren", recycler);
        offsetChildrenHorizontal(fixOffset);
    }

    @Override
    public void scrollToPosition(int position) {
        if (position < 0 || position >= getItemCount())
            return;
        mPendingPosition = position;
        requestLayout();
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    @Nullable
    @Override
    public PointF computeScrollVectorForPosition(int targetPosition) {
        if (getChildCount() == 0) {
            return null;
        }
        View childAt = getChildAt(0);
        int firstChildPos = 0;
        if (childAt != null) {
            firstChildPos = getPosition(childAt);
        }
        int direction = (targetPosition < firstChildPos) ? -1 : 1;
        return new PointF(direction, 0f);
    }

    private void logChildCount(String tag, RecyclerView.Recycler recycler) {
        Log.d(tag, "childCount = $childCount -- scrapSize = ${recycler.scrapList.size}");
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || dx == 0) return 0;
        Log.d("scrollHorizontallyBy", "dx == $dx");

        //填充View，consumed就是修复后的移动值
        int consumed = fill(dx, recycler);
        Log.d("scrollHorizontallyBy", "consumed == $consumed");
        //移动View
        offsetChildrenHorizontal(-consumed);
        //回收View
        recycle(consumed, recycler);

        //输出children
//        logChildCount("scrollHorizontallyBy", recycler)
        return consumed;
    }

    private int fill(int dx, RecyclerView.Recycler recycler) {
        //将要填充的position
        int fillPosition = RecyclerView.NO_POSITION;
        //可用的空间，和onLayoutChildren中的totalSpace类似
        int availableSpace = Math.abs(dx);
        //增加一个滑动距离的绝对值，方便计算
        int absDelta = Math.abs(dx);
        //将要填充的View的左上右下
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        //dx>0就是手指从右滑向左，所以就要填充尾部
        if (dx > 0) {
            View anchorView = getChildAt(getChildCount() - 1);
            int anchorPosition = getPosition(anchorView);
            int anchorRight = getDecoratedRight(anchorView);

            left = anchorRight;
            //填充尾部，那么下一个position就应该是+1
            fillPosition = anchorPosition + 1;

            //如果要填充的position超过合理范围并且最后一个View的
            //right-移动的距离 < 右边缘(width)那就要修正真实能移动的距离
            if (fillPosition >= getItemCount() && anchorRight - absDelta < getWidth()) {
                int fixScrolled = anchorRight - getWidth();
                Log.d("scrollHorizontallyBy", "fill == $fixScrolled");
                return fixScrolled;
            }
            //如果尾部的锚点位置减去dx还是在屏幕外，就不填充下一个View
            if (anchorRight - absDelta > getWidth()) {
                return dx;
            }
        }

        //dx<0就是手指从左滑向右，所以就要填充头部
        if (dx < 0) {
            View anchorView = getChildAt(0);
            int anchorPosition = getPosition(anchorView);
            int anchorLeft = getDecoratedLeft(anchorView);
            right = anchorLeft;
            //填充头部，那么上一个position就应该是-1
            fillPosition = anchorPosition - 1;
            //如果要填充的position超过合理范围并且第一个View的
            //left+移动的距离 > 左边缘(0)那就要修正真实能移动的距离
            if (fillPosition < 0 && anchorLeft + absDelta > 0) {
                return anchorLeft;
            }
            //如果头部的锚点位置加上dx还是在屏幕外，就不填充上一个View
            if (anchorLeft + absDelta < 0) {
                return dx;
            }
        }

        Log.d("scrollHorizontallyBy", "start fillPosition == $fillPosition");

        //根据限定条件，不停地填充View进来
        while (availableSpace > 0 && fillPosition >= 0 && fillPosition < getItemCount()) {
            View itemView = recycler.getViewForPosition(fillPosition);

            if (dx > 0) {
                addView(itemView);
            } else {
                addView(itemView, 0);
            }
            measureChild(itemView, 0, 0);

            if (dx > 0) {
                right = left + getDecoratedMeasuredWidth(itemView);
            } else {
                left = right - getDecoratedMeasuredWidth(itemView);
            }

            bottom = top + getDecoratedMeasuredHeight(itemView);
            layoutDecorated(itemView, left, top, right, bottom);

            if (dx > 0) {
                left += getDecoratedMeasuredWidth(itemView);
                fillPosition++;
            } else {
                right -= getDecoratedMeasuredWidth(itemView);
                fillPosition--;
            }

            if (fillPosition >= 0 && fillPosition < getItemCount()) {
                availableSpace -= getDecoratedMeasuredWidth(itemView);
            }
        }

        Log.d("scrollHorizontallyBy", "end fillPosition == $fillPosition");
        Log.d("scrollHorizontallyBy", "availableSpace == $availableSpace");

//        if (fillPosition < 0 || fillPosition >= itemCount) {
//            if (dx > 0) {
//                val anchorView = getChildAt(childCount - 1)!!
//                Log.d("scrollHorizontallyBy","right == $right")
//                Log.d("scrollHorizontallyBy", "getDecoratedRight == ${getDecoratedRight(anchorView)}")
//                Log.d("scrollHorizontallyBy", "width == ${width}")
//                return getDecoratedRight(anchorView) - width
//            } else {
//                val anchorView = getChildAt(0)!!
//                return getDecoratedLeft(anchorView)
//            }
//        }

        return dx;
    }

    private void recycle(int dx, RecyclerView.Recycler recycler) {
        //要回收View的集合，暂存
        HashSet<View> recycleViews = new HashSet<>();
        //dx>0就是手指从右滑向左，开始填充后面的View，所以要回收前面的children
        if (dx > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                int right = 0;
                if (child != null) {
                    right = getDecoratedRight(child);
                }
                //itemView的right<0就是要超出屏幕要回收View
                if (right >= 0)
                    break;
                recycleViews.add(child);
            }
        }
        //dx<0就是从左滑向右，开始填充前面的View，所以要回收后面的children
        if (dx < 0) {
            for (int i = getChildCount() - 1; i >= 0; i--) {
                View child = getChildAt(i);
                int left = 0;
                if (child != null) {
                    left = getDecoratedLeft(child);
                }
                //itemView的left>recyclerView.width就是要超出屏幕要回收View
                if (left <= getWidth()) break;
                recycleViews.add(child);
            }
        }

        //真正把View移除掉
        for (View view : recycleViews) {
            removeAndRecycleView(view, recycler);
        }
        recycleViews.clear();
    }
}
