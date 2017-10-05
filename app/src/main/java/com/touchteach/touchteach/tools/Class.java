package com.touchteach.touchteach.tools;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Soheil on 8/27/2017.
 */

public class Class {
//    todo add location
//    todo add duration
    private String title;
    private int capacity;
    private String instructor;
    private String limitations = "";
    private int cost;
    private String subject;
    private String description;
    private String timeTable;

    //todo complete add tags
    public final static String TITLE_TAG = "TITLE";
    public final static String CAPACITY_TAG = "CAPACITY";
    public final static String COST_TAG = "COST";
    public final static String DESCRIPTION_TAG = "DESCRIPTION";

    //todo add backend less column const

    public final static String SHANBE_DAY = "SH";
    public final static String EKSHANBE_DAY = "EK";
    public final static String DOSHANBE_DAY = "DO";
    public final static String SESHANBE_DAY = "SE";
    public final static String CHAHARSHANBE_DAY = "CH";
    public final static String PANJSHANBE_DAY = "PA";
    public final static String JOME_DAY = "JO";

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

    public static void save(Class saveClass, AsyncCallback<Map> responder){
        //todo complete save class
        //todo add subject
        //todo add teacher
        //todo add star day
        //todo add final day
        //todo add documents
        HashMap hashMap = new HashMap();
        hashMap.put("title", saveClass.title);
        hashMap.put("description", saveClass.description);
        hashMap.put("limit", saveClass.limitations);
        hashMap.put("cost", saveClass.cost);
        hashMap.put("capacity", saveClass.capacity);
        hashMap.put("time_table", saveClass.timeTable);

        Backendless.Data.of("Class").save(hashMap, responder);
    }

    public void save(AsyncCallback<Map> responder){
        Class.save(this, responder);
    }

    public static void intentSave(Class saveClass, Intent intent){
        //todo complete
        intent.putExtra(TITLE_TAG,saveClass.title);
        intent.putExtra(DESCRIPTION_TAG,saveClass.description);
        intent.putExtra(COST_TAG, saveClass.cost);
        intent.putExtra(CAPACITY_TAG, saveClass.capacity);
    }

    public void intentSave(Intent intent){
        intentSave(this, intent);
    }

    public static Class intentLoad(Intent intent){
        //todo complete
        if (intent.hasExtra(TITLE_TAG)){
            Class load = new Class(intent.getStringExtra(TITLE_TAG));
            //todo set correct default value
            load.setCapacity(intent.getIntExtra(CAPACITY_TAG, 10));
            //todo set correct default value
            load.setCost(intent.getIntExtra(COST_TAG, 100));
            load.setDescription(DESCRIPTION_TAG);
            return load;
        }else
            return null;
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
