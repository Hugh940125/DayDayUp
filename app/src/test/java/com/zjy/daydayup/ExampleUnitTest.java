package com.zjy.daydayup;

import com.zjy.daydayup.DesignPatterns.Recursive.Recursive;
import com.zjy.daydayup.DesignPatterns.Singleton.EnumSingleton;
import com.zjy.daydayup.DesignPatterns.Singleton.HungrySingleton;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkSingleton1() throws Exception {
        EnumSingleton instance1 = EnumSingleton.getInstance();
        EnumSingleton instance2 = EnumSingleton.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    public void checkSingleton2() throws Exception {
        HungrySingleton instance1 = HungrySingleton.getInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    public void checkRecursive() throws Exception {
        Recursive recursive = new Recursive();
        recursive.eatFood(50);
        assertEquals(1, 1);
    }

}