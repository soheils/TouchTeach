package com.touchteach.touchteach.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.LoadRelationsQueryBuilder;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Soheil on 8/17/2017.
 */

//todo in all setter add to preference ?
//    for security add this or not ?
//todo change all getter and setter to write and read properties from BackendlessUser
public class Users extends BackendlessUser{

    //todo complete properties
    private String subjects,gender;
    private String cash;
    private String password;
    private boolean autoSingIn = false;
    private Stack[] messages,classes;

    //class const
    public final static String GENDER_MAN = "man";
    public final static String GENDER_WOMAN = "women";
    //share preferences tags
    private final static String SHARE_PREFERENCES_NAME_TAG = "User";
    private final static String SHARE_PREFERENCES_TAG = "User json";

    //backend less column tags
    private final static String BACKENDLESS_TABLE_NAME = "Users";
    private final static String BACKENDLESS_COLUMN_FIRST_NAME = "first_name";
    private final static String BACKENDLESS_COLUMN_LAST_NAME = "last_name";
    private final static String BACKENDLESS_COLUMN_BIRTH_DAY = "birth_day";
    private final static String BACKENDLESS_COLUMN_GENDER = "gender";
    private final static String BACKENDLESS_COLUMN_MILLI_CODE = "mellicode";
    private final static String BACKENDLESS_COLUMN_CASH = "cash";
    private final static String BACKENDLESS_COLUMN_BIO = "bio";
    private final static String BACKENDLESS_CLASS_CREATED_COLUMN = "ClassCreated";

    public boolean isAutoSingIn() {
        return autoSingIn;
    }
    //todo in all setter when value is change update user property in server
    public void setAutoSingIn(boolean autoSingIn) {
        this.autoSingIn = autoSingIn;
    }

    /**
     * this method return nothing for security
     * @return nothing
     */
    @Override
    public String getPassword() {
        //todo for security use this getPassword or another ?
        return "";
    }

    /**
     * set password in user's property
     * @param password
     */
    @Override
    public void setPassword(String password) {
        //todo when set password auto sing in is true ?
        super.setPassword(password);
        this.password = password;
    }

    /**
     * set email in user's property
     * @param email
     */
    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    /**
     * get email
     * @return if email is empty return null
     */
    @Override
    public String getEmail() {
        String email = super.getEmail();
        return email == null || email.isEmpty() ? null : email;
    }


    /**
     * get biography
     * @return if biography is empty return null
     */
    public String getBio() {
        Object bio = super.getProperty(BACKENDLESS_COLUMN_BIO);
        return bio!= null &&
               bio instanceof String &&
                !bio.toString().isEmpty() ?
                bio.toString() : null;
    }

    /**
     * set biography in user's property
     * @param bio
     */
    public void setBio(String bio) {
        super.setProperty(BACKENDLESS_COLUMN_BIO, bio);
    }

    /**
     * get user's money
     * @return
     */
    public String getCash() {
        return cash;
    }

    /**
     * set user's money in user's property
     * @param cash
     */
    public void setCash(String cash) {
        this.cash = cash;
    }

    /**
     * get user's first name
     * @return if fist name is empty return null
     */
    public String getFirstName() {
        Object firstName = super.getProperty(BACKENDLESS_COLUMN_FIRST_NAME);
        return firstName != null &&
                firstName instanceof String &&
                !((String)firstName).isEmpty() ?
                firstName.toString() : null;
    }

    /**
     * set user's first name in user's property
     * @param firstName
     */
    public void setFirstName(String firstName) {
        super.setProperty(BACKENDLESS_COLUMN_FIRST_NAME, firstName);
    }

    /**
     * get user's last name
     * @return if user's last name is empty return null
     */
    public String getLastName() {
        Object lastName = super.getProperty(BACKENDLESS_COLUMN_LAST_NAME);
        return lastName != null &&
                lastName instanceof String &&
                !lastName.toString().isEmpty() ?
                lastName.toString() : null;
    }

    /**
     * set user's last name in user's property
     * @param lastName
     */
    public void setLastName(String lastName) {
        super.setProperty(BACKENDLESS_COLUMN_LAST_NAME, lastName);
    }

    //what's this?
    public String getSubjects() {
        return subjects;
    }

    /**
     * get user's gender
     * @return gender{man, woman}
     */
    public String getGender() {
        return gender;
    }

    /**
     * set user's gender in user's property
     * if gender don't include correct gender throw IllegalArgumentException
     * @param gender
     * @throws IllegalArgumentException
     */
    public void setGender (String gender){
        gender = gender.toLowerCase();
        if (gender.equals(GENDER_MAN) || gender.equals(GENDER_WOMAN))
            this.gender = gender;
        else
            throw new IllegalArgumentException(gender + ": gender most be include " + GENDER_MAN + " or " + GENDER_WOMAN);
    }

    /**
     * get user's birthday in string
     * @return birthday
     */
    public String getBirthDay(){
        Object birth = super.getProperty(BACKENDLESS_COLUMN_BIRTH_DAY);
        return birth != null &&
                birth instanceof String &&
                !birth.toString().isEmpty() ?
                birth.toString() : null;
    }

    /**
     * set user's birthday in user's birthday
     * @param birthDay
     */
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

                setFromServer(response);

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

    /**
     * set all user's property from server
     * @param server
     */
    private void setFromServer(BackendlessUser server){
        this.putProperties(server.getProperties());
    }

    /**
     * update user in server
     * @param asyncCallback
     */
    public void update(AsyncCallback<BackendlessUser> asyncCallback){
        Backendless.UserService.update(this, asyncCallback);
    }

    /**
     * logout user
     * @param context
     * @param responder
     */
    public void logout(final Context context, final AsyncCallback<Void> responder){
        Backendless.UserService.logout(new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Users.sharePreferenceDelete(context);
                responder.handleResponse(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                responder.handleFault(fault);
            }
        });
    }

    /**
     * add class for this user
     * @param asyncCallback
     * @param saveClasses
     */
    public void addClasses(AsyncCallback<Integer> asyncCallback, Map... saveClasses){
        Backendless.Data.of(BackendlessUser.class).addRelation(this, BACKENDLESS_CLASS_CREATED_COLUMN, Arrays.asList(saveClasses),asyncCallback);
    }

    /**
     * add class for this class
     * whit default manage error
     * @param saveClasses
     */
    public void addClasses(Map... saveClasses){
        addClasses(new AsyncCallback<Integer>() {
            @Override
            public void handleResponse(Integer integer) {
                //todo manage it
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                //todo manage it
            }
        },saveClasses);
    }


    public List<Class>  CreatedClasses (){
        final List<Class> result = new ArrayList<>();

        LoadRelationsQueryBuilder<Map<String ,Object>> query = LoadRelationsQueryBuilder.ofMap();
        query.setRelationName(BACKENDLESS_CLASS_CREATED_COLUMN);
        //todo handle error if often
        List<Map<String ,Object>> mapList= Backendless.Data.of(BackendlessUser.class).loadRelations(this.getUserId(), query);
        Map<String ,Object>[] mapArray;
        mapArray = new Map[mapList.size()];
        mapList.toArray(mapArray);

        for (Map<String ,Object> map : mapArray)
            result.add(Class.parseToClass(map));

        return result;
    }
}
