package com.zjy.daydayup.DesignPatterns.Observer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Hugh on 2018/6/20.
 *
 */

public class Sender implements IObserverable{

    private final ArrayList<IObserver> observerList;

    public Sender(){
        observerList = new ArrayList<>();
    }

    @Override
    public void addObserver(IObserver Observer) {
        observerList.add(Observer);
    }

    @Override
    public void removeObserver(IObserver Observer) {
        observerList.remove(Observer);
    }

    @Override
    public void notifyObserver(String str) {
        Iterator<IObserver> iterator = observerList.iterator();
        while (iterator.hasNext()){
            iterator.next().OnUpdate(str);
        }
    }
}
