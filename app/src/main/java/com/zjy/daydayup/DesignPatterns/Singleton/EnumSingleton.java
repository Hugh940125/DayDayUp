package com.zjy.daydayup.DesignPatterns.Singleton;

/**
 * Created by Hugh on 2018/6/19.
 * 用枚举的方法实现单例
 */

public class EnumSingleton {

    public static EnumSingleton getInstance(){
        return singleton.INSTANCE.getInstance();
    }

    private enum singleton{
        INSTANCE;

        private final EnumSingleton enumSingleton;

        singleton(){
            enumSingleton = new EnumSingleton();
        }

        public EnumSingleton getInstance(){
            return enumSingleton;
        }
    }
}
