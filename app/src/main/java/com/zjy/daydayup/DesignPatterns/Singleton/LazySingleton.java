package com.zjy.daydayup.DesignPatterns.Singleton;

/**
 * Created by Hugh on 2018/6/19.
 * 懒汉式单例
 */

public class LazySingleton {

    private LazySingleton() {}

    private static LazySingleton single = null;

    //加同步
    public static synchronized LazySingleton getInstance1() {
        if (single == null) {
            single = new LazySingleton();
        }
        return single;
    }

    //双重检查锁定
    public static synchronized LazySingleton getInstance2() {
        if (single == null) {
            synchronized (LazySingleton.class) {
                if (single == null) {
                    single = new LazySingleton();
                }
            }
        }
        return single;
    }

    private static class LazyHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }
    public static final LazySingleton getInstance3() {
        return LazyHolder.INSTANCE;
    }
}
