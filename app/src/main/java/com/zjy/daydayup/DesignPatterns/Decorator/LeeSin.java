package com.zjy.daydayup.DesignPatterns.Decorator;

import android.util.Log;

/**
 * Created by Hugh on 2018/6/22.
 *
 */

public class LeeSin implements Hero {

    @Override
    public void learnSkill() {
        Log.e("学习了技能",",如上所示");
    }
}
