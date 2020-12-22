package com.zjy.daydayup.DesignPatterns.Observer;

/**
 * Created by Hugh on 2018/6/19.
 *
 */

public interface IObserverable {


    public void addObserver(IObserver Observer);

    public void removeObserver(IObserver Observer);

    public void notifyObserver(String str);
}
