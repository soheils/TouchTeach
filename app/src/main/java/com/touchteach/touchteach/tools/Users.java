package com.touchteach.touchteach.tools;

import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Soheil on 8/17/2017.
 */

public class Users {

    //todo clean properties
    private String email,fname,lname,age,subjects,id,gender;
    private String cash, Bio;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAge() {
        return age;
    }

    public String getSubjects() {
        return subjects;
    }
    public Stack[] messages,classes;
    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }


    public Users(BackendlessUser user) {
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.fname = user.getProperty("fname").toString();
        this.lname = user.getProperty("lname").toString();
        this.gender = user.getProperty("gender").toString();
    }

    public Users(){
        //todo change it if necessary
    }

    public void login(String password, AsyncCallback<BackendlessUser> asyncCallback){
        Users.login(email, password, asyncCallback);
    }

    public void login(AsyncCallback<BackendlessUser> asyncCallback){
        login(password, asyncCallback);
    }

    public static void login(String email, String password, AsyncCallback<BackendlessUser> asyncCallback){
        Backendless.UserService.login(email, password, asyncCallback, true);
    }
}
