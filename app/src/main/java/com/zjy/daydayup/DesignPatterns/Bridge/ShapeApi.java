package com.zjy.daydayup.DesignPatterns.Bridge;

/**
 * Created by Hugh on 2018/6/21.
 *
 */

public abstract class ShapeApi {

    protected final DrawApi drawApi;

    protected ShapeApi(DrawApi drawApi){
        this.drawApi = drawApi;
    }

    public abstract void draw();
}
