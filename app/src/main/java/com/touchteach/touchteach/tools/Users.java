package com.touchteach.touchteach.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.gson.Gson;

import java.util.Stack;

/**
 * Created by Soheil on 8/17/2017.
 */

//todo in all setter add to preference ?
//    for security add this or not ?
//todo change all getter and setter to write and read properties from BackendlessUser
public class Users extends BackendlessUser{

    //todo complete properties
    private String age,subjects,gender;
    private String cash;
    private String password;
    private boolean autoSingIn = false;
    private Stack[] messages,classes;

    //share preferences tags
    private final static String SHARE_PREFERENCES_NAME_TAG = "User";
    private final static String SHARE_PREFERENCES_TAG = "User json";

    //backend less column tags
    private final static String BACKENDLESS_COLUMN_FIRST_NAME = "first_name";
    private final static String BACKENDLESS_COLUMN_LAST_NAME = "last_name";
    private final static String BACKENDLESS_COLUMN_BIRTH_DAY = "birth_day";
    private final static String BACKENDLESS_COLUMN_GENDER = "gender";
    private final static String BACKENDLESS_COLUMN_MILLI_CODE = "mellicode";
    private final static String BACKENDLESS_COLUMN_CASH = "cash";
    private final static String BACKENDLESS_COLUMN_BIO = "bio";

    public boolean isAutoSingIn() {
        return autoSingIn;
    }
    //todo in all setter when value is change update user property in server
    public void setAutoSingIn(boolean autoSingIn) {
        this.autoSingIn = autoSingIn;
    }

    @Override
    public String getPassword() {
        //todo for security use this getPassword or another ?
        return "";
    }

    @Override
    public void setPassword(String password) {
        //todo when set password auto sing in is true ?
        super.setPassword(password);
        this.password = password;
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getEmail() {
        String email = super.getEmail();
        return email == null || email.isEmpty() ? null : email;
    }



    public String getBio() {
        Object bio = super.getProperty(BACKENDLESS_COLUMN_BIO);
        return bio!= null &&
               bio instanceof String &&
                !bio.toString().isEmpty() ?
                bio.toString() : null;
    }

    public void setBio(String bio) {
        super.setProperty(BACKENDLESS_COLUMN_BIO, bio);
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }


    public String getFirstName() {
        Object firstName = super.getProperty(BACKENDLESS_COLUMN_FIRST_NAME);
        return firstName != null &&
                firstName instanceof String &&
                !((String)firstName).isEmpty() ?
                firstName.toString() : null;
    }

    public void setFirstName(String firstName) {
        super.setProperty(BACKENDLESS_COLUMN_FIRST_NAME, firstName);
    }

    public String getLastName() {
        Object lastName = super.getProperty(BACKENDLESS_COLUMN_LAST_NAME);
        return lastName != null &&
                lastName instanceof String &&
                !lastName.toString().isEmpty() ?
                lastName.toString() : null;
    }

    public void setLastName(String lastName) {
        super.setProperty(BACKENDLESS_COLUMN_LAST_NAME, lastName);
    }

    public String getAge() {
        return age;
    }

    public String getSubjects() {
        return subjects;
    }


    public String getGender() {
        return gender;
    }

    public String getBirthDay(){
        Object birth = super.getProperty(BACKENDLESS_COLUMN_BIRTH_DAY);
        return birth != null &&
                birth instanceof String &&
                !birth.toString().isEmpty() ?
                birth.toString() : null;
    }

    public void setBirthDay(String birthDay){
        //todo check input string is correct
        super.setProperty(BACKENDLESS_COLUMN_BIRTH_DAY, birthDay);
    }


    public Users(BackendlessUser user) {
        setFromServer(user);
    }

    public Users(){
        //todo change it if necessary
    }



    // login methods

    /**
     * login whit email and password
     * @param email
     * @param password
     * @param asyncCallback
     * @param context
     */
    public void login(final Context context, String email, String password, final AsyncCallback<BackendlessUser> asyncCallback){
        Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                asyncCallback.handleResponse(response);

                setFromServer(Backendless.UserService.CurrentUser());

                sharePreferenceSave(context);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                asyncCallback.handleFault(fault);
            }
        }, true);
    }

    /**
     * login this password
     * email is store
     * @param password
     * @param asyncCallback
     * @param context
     */
    public void login(Context context, String password, AsyncCallback<BackendlessUser> asyncCallback){
        login(context, getEmail(), password, asyncCallback);
    }

    /**
     * all parameters is stored
     * @param asyncCallback
     * @param context
     */
    public void login(Context context, AsyncCallback<BackendlessUser> asyncCallback){
        login(context, password, asyncCallback);
    }


    // share preferences methods

    /**
     * save all user parameters in share preference
     * @param context
     */
    public void sharePreferenceSave(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARE_PREFERENCES_NAME_TAG, Context.MODE_PRIVATE).edit();

        Gson gson = new Gson();
        String json = gson.toJson(this);
        editor.putString(SHARE_PREFERENCES_TAG,json);

        editor.apply();
    }

    /**
     * delete user saved in share preference
     * @param context
     */
    public static void sharePreferenceDelete(Context context){
        SharedPreferences.Editor edit = context.getSharedPreferences(SHARE_PREFERENCES_NAME_TAG, Context.MODE_PRIVATE).edit();
        edit.clear();
        edit.apply();
    }

    /**
     * return user saved in share preference
     * @param context
     * @return user saved
     */
    public static Users sharePreferenceLoad(Context context){
        SharedPreferences preferences = context.getSharedPreferences(SHARE_PREFERENCES_NAME_TAG, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = preferences.getString(SHARE_PREFERENCES_TAG, "");
        Users users = gson.fromJson(json, Users.class);

        return users == null ? new Users():users;
    }


    private void setFromServer(BackendlessUser server){
        this.putProperties(server.getProperties());
    }

    public void update(AsyncCallback<BackendlessUser> asyncCallback){
        Backendless.UserService.update(this, asyncCallback);
    }
}
