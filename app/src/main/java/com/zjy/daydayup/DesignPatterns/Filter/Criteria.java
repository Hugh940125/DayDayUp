package com.zjy.daydayup.DesignPatterns.Filter;

/**
 * Created by Hugh on 2018/6/21.
 */

import java.util.List;

public interface Criteria {
    public List<Student> meetCriteria(List<Student> persons);
}

