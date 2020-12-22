package com.zjy.daydayup.DesignPatterns.Singleton;

/**
 * Created by Hugh on 2018/6/19.
 * 饿汉式单例
 * 产生一个静态的final变量，天生的线程安全性
 */

public class HungrySingleton {

    private HungrySingleton(){}

    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }
}
