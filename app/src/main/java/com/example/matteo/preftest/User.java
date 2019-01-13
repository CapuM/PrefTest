package com.example.matteo.preftest;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList interests;
    private FirebaseUser fUser;

    public User(){}

    public User(String name){
        this.name=name;
    }

    public User(String name,FirebaseUser firebaseUser){
        this.name=name;
        this.fUser=firebaseUser;
    }
    public User(String name,ArrayList interests){
        this.name=name;
        this.interests=interests;

    }

    public String getName() {
        return name;
    }

    public ArrayList getInterests() {
        return interests;
    }

    public FirebaseUser getfUser() {
        return fUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInterests(ArrayList interests) {
        this.interests = interests;
    }

    public void setfUser(FirebaseUser fUser) {
        this.fUser = fUser;
    }
}
