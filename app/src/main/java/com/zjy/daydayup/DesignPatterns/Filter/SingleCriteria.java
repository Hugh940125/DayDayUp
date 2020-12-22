package com.zjy.daydayup.DesignPatterns.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugh on 2018/6/21.
 *
 */

public class SingleCriteria implements Criteria {
    @Override
    public List<Student> meetCriteria(List<Student> persons) {
        ArrayList<Student> SingleStudents = new ArrayList<>();
        for (Student student:persons){
            if (student.getStatus().equals("single")){
                SingleStudents.add(student);
            }
        }
        return SingleStudents;    }
}
