package com.zjy.daydayup.DesignPatterns.Filter;

import java.util.List;

/**
 * Created by Hugh on 2018/6/21.
 *
 */

public class OrCriteria implements Criteria {

    private final Criteria criteria1;
    private final Criteria criteria2;

    public OrCriteria(Criteria criteria1, Criteria criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public List<Student> meetCriteria(List<Student> persons) {

        List<Student> MaleStudents = criteria1.meetCriteria(persons);
        List<Student> SingleStudents = criteria2.meetCriteria(persons);

        for (Student student:MaleStudents){
            if (!SingleStudents.contains(student)){
                SingleStudents.add(student);
            }
        }
        return SingleStudents;
    }
}
