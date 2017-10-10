package com.touchteach.touchteach.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;

import java.util.Stack;

/**
 * Created by Soheil on 8/17/2017.
 */



public class Users {

    //todo clean properties
    private String email,fname,lname,age,subjects,id,gender;
    private String cash, Bio;
    private String password;
    private boolean autoSingIn = false;

    //todo complete
    //share preferences tags
    public final static String SHARE_PREFERENCES_NAME_TAG = "User";
    public final static String SHARE_PREFERENCES_EMAIL_TAG = "Email";
    public final static String SHARE_PREFERENCES_PASSWORD_TAG = "Password";
    public final static String SHARE_PREFERENCES_FIRST_NAME_TAG = "First name";
    public final static String SHARE_PREFERENCES_LAST_NAME_TAG = "Last name";
    public final static String SHARE_PREFERENCES_AUTO_SING_IN_TAG = "Auto sing in";

    public boolean isAutoSingIn() {
        return autoSingIn;
    }

    public void setAutoSingIn(boolean autoSingIn) {
        this.autoSingIn = autoSingIn;
    }

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

    public void sharePrefrenceSave(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARE_PREFERENCES_NAME_TAG, Context.MODE_PRIVATE).edit();

        //todo complete all user properties
        editor.putString(SHARE_PREFERENCES_EMAIL_TAG, email);
        editor.putString(SHARE_PREFERENCES_PASSWORD_TAG, password);
        editor.putBoolean(SHARE_PREFERENCES_AUTO_SING_IN_TAG, autoSingIn);

        editor.apply();
    }

    public static void sharePrefrencedelete(Context context){
        SharedPreferences.Editor edit = context.getSharedPreferences(SHARE_PREFERENCES_NAME_TAG, Context.MODE_PRIVATE).edit();
        edit.clear();
        edit.apply();
    }

    public static Users sharepreferenceLoad(Context context){
        SharedPreferences preferences = context.getSharedPreferences(SHARE_PREFERENCES_NAME_TAG, Context.MODE_PRIVATE);

        //todo complete
        Users users = new Users();
        users.setEmail(preferences.getString(SHARE_PREFERENCES_EMAIL_TAG, null));
        users.setPassword(preferences.getString(SHARE_PREFERENCES_PASSWORD_TAG, null));
        users.setAutoSingIn(preferences.getBoolean(SHARE_PREFERENCES_AUTO_SING_IN_TAG, false));

        return users;
    }
}
