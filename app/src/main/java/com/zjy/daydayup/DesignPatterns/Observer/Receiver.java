package com.zjy.daydayup.DesignPatterns.Observer;

import android.util.Log;

/**
 * Created by Hugh on 2018/6/20.
 *
 */

public class Receiver implements IObserver{

    @Override
    public void OnUpdate(String str) {
        Log.e("收到了",str);
    }
}
