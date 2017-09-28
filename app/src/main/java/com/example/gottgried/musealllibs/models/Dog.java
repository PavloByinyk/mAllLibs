package com.example.gottgried.musealllibs.models;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by Gottgried on 28.09.2017.
 */

public class Dog extends RealmObject {

    private String name;
    private String mark;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
