package com.touchteach.touchteach.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Stack;

/**
 * Created by Soheil on 8/17/2017.
 */

//todo in all setter add to preference ?
//    for security add this or not ?

public class Users {

    //todo clean properties
    private String email, firstName, lastName,age,subjects,id,gender;
    private String cash, Bio;
    private String password;
    private boolean autoSingIn = false;

    //todo complete
    //share preferences tags
    //todo private user share preferences name
    public final static String SHARE_PREFERENCES_NAME_TAG = "User";
    public final static String SHARE_PREFERENCES_EMAIL_TAG = "Email";
    public final static String SHARE_PREFERENCES_PASSWORD_TAG = "Password";
    public final static String SHARE_PREFERENCES_FIRST_NAME_TAG = "First name";
    public final static String SHARE_PREFERENCES_LAST_NAME_TAG = "Last name";
    public final static String SHARE_PREFERENCES_AUTO_SING_IN_TAG = "Auto sing in";

    private final static String BACKENDLESS_COLUMN_EMAIL = "email";
    private final static String BACKENDLESS_COLUMN_FIRST_NAME = "first_name";
    private final static String BACKENDLESS_COLUMN_LAST_NAME = "last_name";
    private final static String BACKENDLESS_COLUMN_BIRTH_DAY = "birth_day";
    private final static String BACKENDLESS_COLUMN_GENDER = "gender";
    private final static String BACKENDLESS_COLUMN_MILLI_CODE = "mellicode";
    private final static String BACKENDLESS_COLUMN_CASH = "cash";
    private final static String BACKENDLESS_COLUMN_PASSWORD = "password";

    public boolean isAutoSingIn() {
        return autoSingIn;
    }
    //todo in all setter when value is change update user property in server
    public void setAutoSingIn(boolean autoSingIn) {
        this.autoSingIn = autoSingIn;
    }

    public void setPassword(String password) {
        //todo when set password auto sing in is true ?
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        this.firstName = user.getProperty("firstName").toString();
        this.lastName = user.getProperty("lastName").toString();
        this.gender = user.getProperty("gender").toString();
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
        //todo get another property from server and set it except password
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
        login(context, email, password, asyncCallback);
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

        //todo complete all user properties
        editor.putString(SHARE_PREFERENCES_EMAIL_TAG, email);
        editor.putString(SHARE_PREFERENCES_PASSWORD_TAG, password);
        editor.putBoolean(SHARE_PREFERENCES_AUTO_SING_IN_TAG, autoSingIn);
        editor.putString(SHARE_PREFERENCES_FIRST_NAME_TAG, firstName);
        editor.putString(SHARE_PREFERENCES_LAST_NAME_TAG, lastName);

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

        //todo complete
        Users users = new Users();
        users.setEmail(preferences.getString(SHARE_PREFERENCES_EMAIL_TAG, null));
        users.setPassword(preferences.getString(SHARE_PREFERENCES_PASSWORD_TAG, null));
        users.setAutoSingIn(preferences.getBoolean(SHARE_PREFERENCES_AUTO_SING_IN_TAG, false));
        users.firstName = preferences.getString(SHARE_PREFERENCES_FIRST_NAME_TAG, null);
        users.lastName = preferences.getString(SHARE_PREFERENCES_LAST_NAME_TAG, null);

        return users;
    }


    private void setFromServer(BackendlessUser server){
        this.firstName = (String) server.getProperty(BACKENDLESS_COLUMN_FIRST_NAME);
        this.lastName = (String) server.getProperty(BACKENDLESS_COLUMN_LAST_NAME);

    }
}
