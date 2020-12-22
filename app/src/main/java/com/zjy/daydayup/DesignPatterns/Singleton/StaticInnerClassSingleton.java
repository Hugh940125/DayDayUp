package com.zjy.daydayup.DesignPatterns.Singleton;

/**
 * Created by Hugh on 2018/6/20.
 *
 */

public class StaticInnerClassSingleton {
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    private StaticInnerClassSingleton (){}
    public static final StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}