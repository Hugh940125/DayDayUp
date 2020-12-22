package com.zjy.daydayup.DesignPatterns.Factory;

import android.util.Log;

/**
 * Created by Hugh on 2018/6/20.
 *
 */

public class Cat implements IAnimal {
    @Override
    public void eat() {
        Log.e("cat--","吃鱼");
    }

    @Override
    public void drink() {
        Log.e("cat--","喝水");
    }
}
