package com.zjy.daydayup.DesignPatterns.Factory;

import android.util.Log;

/**
 * Created by Hugh on 2018/6/20.
 */

public class Dog implements IAnimal {
    @Override
    public void eat() {
        Log.e("dog--","吃骨头");
    }

    @Override
    public void drink() {
        Log.e("dog--","喝水");
    }
}
