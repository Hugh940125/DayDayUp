package com.zjy.daydayup.DesignPatterns.Recursive;

import android.util.Log;

/**
 * Created by Hugh on 2018/7/17.
 *
 */

public class Recursive {

    public void eatFood(int foodAmount){
        foodAmount--;
        Log.e("Recursive","还剩"+foodAmount+"个");
        if (foodAmount <= 0){
            Log.e("Recursive","吃完了");
        }else {
            eatFood(foodAmount);
        }
    }
}
