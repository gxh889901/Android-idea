package com.example.test.javalib.algorithms;

import com.example.test.javalib.javabase.Object.User;

public class WomanUser  extends User {
    public WomanUser(){
        name = "WomanUser"; //不在同一个包下，但是name的修饰符是protected所以也能访问
    }
}
