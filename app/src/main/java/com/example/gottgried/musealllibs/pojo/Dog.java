package com.example.gottgried.musealllibs.pojo;

import android.text.Editable;

import io.realm.RealmObject;

/**
 * Created by Gottgried on 28.09.2017.
 */

public class Dog extends RealmObject {

    private String name;
    private String mark;
    private int age;

    public Dog(String name, String mark, int age) {
        this.name = name;
        this.mark = mark;
        this.age = age;
    }

    public Dog() {
    }

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
