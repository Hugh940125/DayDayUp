package com.zjy.daydayup.DesignPatterns.Bridge;

import android.util.Log;

/**
 * Created by Hugh on 2018/6/20.
 *
 */

public class RedCircle implements DrawApi {
    @Override
    public void drawCircle(int radius, int x, int y) {
        Log.e("drawCircle","redCircle");
    }
}
