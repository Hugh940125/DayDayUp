package com.zjy.daydayup.DesignPatterns.Filter;

/**
 * Created by Hugh on 2018/6/21.
 *
 */

public class Student {
    private String name;
    private String gender;
    private String status;

    public Student(String name, String gender, String score) {
        this.name = name;
        this.gender = gender;
        this.status = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
