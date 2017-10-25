package com.touchteach.touchteach.tools;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Soheil on 8/27/2017.
 */

public class Class {
//    todo add location
//    todo add duration
    private String title = "";
    private int capacity = 0;
    private String instructor = "";
    private String limitations = "";
    private int cost = 0;
    private String subject = "";
    private String description = "";
    private String timeTable = "";

    //todo complete add tags
    //intent tag
    private final static String SAVE_NAME_TAG = "CLASS";

    //todo add backend less column const
    private final static String BACKENDLESS_TABLE_NAME = "Class";
    private final static String BACKENDLESS_TITLE_COLUMN = "title";
    private final static String BACKENDLESS_DESCRIPTION_COLUMN = "description";
    private final static String BACKENDLESS_LIMITS_COLUMN = "limit";
    private final static String BACKENDLESS_COST_COLUMN = "cost";
    private final static String BACKENDLESS_CAPACITY_COLUMN = "capacity";
    private final static String BACKENDLESS_TIME_TABLE_COLUMN = "time_table";
    private final static String BACKENDLESS_CREATOR_COLUMN = "creator";


    //day tag
    public final static String SHANBE_DAY = "SH";
    public final static String EKSHANBE_DAY = "EK";
    public final static String DOSHANBE_DAY = "DO";
    public final static String SESHANBE_DAY = "SE";
    public final static String CHAHARSHANBE_DAY = "CH";
    public final static String PANJSHANBE_DAY = "PA";
    public final static String JOME_DAY = "JO";
    public final static String[] DAY_TAGS = {
            SHANBE_DAY
            ,EKSHANBE_DAY
            ,DOSHANBE_DAY
            ,SESHANBE_DAY
            ,CHAHARSHANBE_DAY
            ,PANJSHANBE_DAY
            ,JOME_DAY};



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty())
            this.title = title;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCapacity(String  capacity) {
        try {
            int cap = Integer.parseInt(capacity);
            setCapacity(cap);
        }catch (NumberFormatException exp){
            //todo manage it
            Log.e("Touch Teach","input item is incorrect");
        }
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLimitations() {
        return limitations;
    }

    //todo complete this method
    public void addLimitations(){

    }

    public void clearLimitations(){
        limitations = "";
    }


    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCost(String cost){
        try {
            int integerCost = Integer.parseInt(cost);
            setCost(integerCost);
        }catch (NumberFormatException exp){
            //todo manage it
            Log.e("Touch Teach","input item is incorrect");
        }
    }
    public Class(String name){
        setTitle(name);
    }

    public static void save(Class saveClass, final AsyncCallback<Integer> responder, final Context context){
        //todo complete save class
        //todo add subject
        //todo add teacher
        //todo add star day
        //todo add final day
        //todo add documents
        HashMap hashMap = new HashMap();
        hashMap.put(BACKENDLESS_TITLE_COLUMN, saveClass.title);
        hashMap.put(BACKENDLESS_DESCRIPTION_COLUMN, saveClass.description);
        hashMap.put(BACKENDLESS_LIMITS_COLUMN, saveClass.limitations);
        hashMap.put(BACKENDLESS_COST_COLUMN, saveClass.cost);
        hashMap.put(BACKENDLESS_CAPACITY_COLUMN, saveClass.capacity);
        hashMap.put(BACKENDLESS_TIME_TABLE_COLUMN, saveClass.timeTable);

        Backendless.Data.of(BACKENDLESS_TABLE_NAME).save(hashMap, new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {
                ArrayList<Map> arrayList = new ArrayList();
                arrayList.add(Users.sharePreferenceLoad(context).getProperties());
                Backendless.Data.of(BACKENDLESS_TABLE_NAME).setRelation(response, BACKENDLESS_CREATOR_COLUMN, arrayList, new AsyncCallback<Integer>() {
                    @Override
                    public void handleResponse(Integer response) {
                        responder.handleResponse(response);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        responder.handleFault(fault);
                    }
                });
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                //todo if fault delete class
                responder.handleFault(fault);
            }
        });
//        Backendless.Data.of(BACKENDLESS_TABLE_NAME).setRelation()
    }

    public void save(AsyncCallback<Integer> responder, Context context){
        Class.save(this, responder, context);
    }

    public static void intentSave(Class saveClass, Intent intent){
        //todo use json
        Gson gson = new Gson();
        String json = gson.toJson(saveClass);

        intent.putExtra(SAVE_NAME_TAG, json);
//        intent.putExtra(TITLE_TAG,saveClass.title);
//        intent.putExtra(DESCRIPTION_TAG,saveClass.description);
//        intent.putExtra(COST_TAG, saveClass.cost);
//        intent.putExtra(CAPACITY_TAG, saveClass.capacity);
//        intent.putExtra(TIME_TABLE_TAG, saveClass.timeTable);
    }

    public void intentSave(Intent intent){
        intentSave(this, intent);
    }

    public static Class intentLoad(Intent intent){
        //todo use json
        Class load = null;
        if (intent.hasExtra(SAVE_NAME_TAG)){
            String json = intent.getStringExtra(SAVE_NAME_TAG);
            load = new Gson().fromJson(json, Class.class);
        }
        return load;
    }

    public void addDayToTimeTable(String dayTag, int startHour, int startMinute, int endHour, int endMinute){
        checkDayTag(dayTag);
        if (hasDayInTimeTable(dayTag))
            deleteDayFromTimeTable(dayTag);
        timeTable = timeTable + dayTag + startHour + "." + startMinute + "-" + endHour + "." + endMinute + "|";
    }

    public void deleteDayFromTimeTable(String dayTag){
        checkDayTag(dayTag);
        if (hasDayInTimeTable(dayTag)){
            timeTable = timeTable.replace(getDayFromTimeTable(dayTag)+"|","");
        }
    }

    public boolean hasDayInTimeTable(String dayTag){
        checkDayTag(dayTag);
        return timeTable.contains(dayTag);
    }

    public String getDayFromTimeTable(String dayTag){
        checkDayTag(dayTag);
        if (hasDayInTimeTable(dayTag)){
            String[] days = timeTable.split("|");
            for (String day : days)
                if (day.contains(dayTag))
                    return day;
        }
        return null;
    }

    public void clearTimeTable(){
        this.timeTable = "";
    }

    private static void checkDayTag (String dayTag){
        boolean flag = dayTag.equals(SHANBE_DAY) ||
                dayTag.equals(EKSHANBE_DAY) ||
                dayTag.equals(DOSHANBE_DAY) ||
                dayTag.equals(SESHANBE_DAY) ||
                dayTag.equals(CHAHARSHANBE_DAY) ||
                dayTag.equals(PANJSHANBE_DAY) ||
                dayTag.equals(JOME_DAY);
        if (!flag)
           throw new IllegalArgumentException("day tag uncorrected :" + dayTag);
    }
}
