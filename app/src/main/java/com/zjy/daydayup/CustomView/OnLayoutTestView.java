package com.zjy.daydayup.CustomView;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Hugh on 2018/6/26.
 *
 */

public class OnLayoutTestView extends ViewGroup{

    private TextView textView;
    private Button button;

    public OnLayoutTestView(Context context) {
        super(context);
    }

    public OnLayoutTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childAt = getChildAt(0);
        childAt.layout(20,20,200,500);
    }
}
