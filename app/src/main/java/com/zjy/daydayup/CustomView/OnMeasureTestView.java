package com.zjy.daydayup.CustomView;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Hugh on 2018/6/26.
 *
 */

public class OnMeasureTestView extends View{

    private int width;
    private int height;

    public OnMeasureTestView(Context context) {
        super(context);
    }

    public OnMeasureTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int mHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (mWidthMode == MeasureSpec.AT_MOST){
            width = Math.min(widthSize,500);
        }else if (mWidthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }


        int heightSize = MeasureSpec.getSize(widthMeasureSpec);
        if (mHeightMode == MeasureSpec.AT_MOST){
            height = Math.min(heightSize,500);
        }else if (mHeightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }

        setMeasuredDimension(width,height);
    }

}
