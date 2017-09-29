package com.touchteach.touchteach.tools;

import android.content.Context;
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
    private String title;
    private int capacity;
    private String instructor;
    private String limitations;
    private int cost;
    public  String subject;
    public  String description;
//    todo add location
//    todo add class starting time
//    todo add duration
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

    public void setLimitations(String limitations) {
        this.limitations = limitations;
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

    public static void save(Class saveClass, final Context context){
        //todo complete save class
        //todo add subject
        //todo add description
        //todo add teacher
        //todo add star day
        //todo add final day
        //todo add limits
        //todo add capacity
        HashMap hashMap = new HashMap();
        hashMap.put("title", saveClass.title);
        Backendless.Data.of("Class").save(hashMap, new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {
                //todo manage it
                Log.d("Touch Teach", "class saved");
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                //todo manage it
                Log.d("Touch Teach", "class can't save");
                Log.d("Touch Teach", fault.toString());
            }
        });
    }

    public void save(Context context){
        Class.save(this, context);
    }

}
