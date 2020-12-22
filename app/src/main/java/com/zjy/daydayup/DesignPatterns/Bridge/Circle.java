package com.zjy.daydayup.DesignPatterns.Bridge;

/**
 * Created by Hugh on 2018/6/21.
 *
 */

public class Circle extends ShapeApi {

    private final int radius;
    private final int x;
    private final int y;

    public Circle(int x, int y, int radius, DrawApi drawApi) {
        super(drawApi);
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        drawApi.drawCircle(radius,x,y);
    }
}
