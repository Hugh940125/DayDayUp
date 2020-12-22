package com.zjy.daydayup.DesignPatterns.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugh on 2018/6/21.
 *
 */

public class MaleCriteria implements Criteria {
    @Override
    public List<Student> meetCriteria(List<Student> persons) {
        ArrayList<Student> MaleStudents = new ArrayList<>();
        for (Student student:persons){
            if (student.getGender().equals("male")){
                MaleStudents.add(student);
            }
        }
        return MaleStudents;
    }
}
