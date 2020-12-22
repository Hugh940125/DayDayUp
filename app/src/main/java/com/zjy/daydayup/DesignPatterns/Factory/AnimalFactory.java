package com.zjy.daydayup.DesignPatterns.Factory;

import android.util.Log;

/**
 * Created by Hugh on 2018/6/20.
 *
 */

public class AnimalFactory {

    public IAnimal getAnimal(String animalName){
        switch (animalName){
            case "cat":
                return new Cat();
            case "dog":
                return new Dog();
            default:
                Log.e("AnimalFactory","error animal name");
                break;
        }
        return null;
    }
}
