package com.touchteach.touchteach.tools;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Date;

/**
 * Created by Soheil on 8/27/2017.
 */

public class Class {
    private String className;
    private int capacity;
    private String instructor;
    private String limitations;
    private int cost;
    private Date date;
    public  String subject;
    public  String description;
//    todo add location
//    todo add create date
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Class(String name){
        setClassName(name);
    }

    public static void save(Class saveClass, final Context context){
        //todo save class
        Backendless.Data.of(Class.class).save(saveClass, new AsyncCallback<Class>() {
            @Override
            public void handleResponse(Class response) {
                Toast.makeText(context, "کلاس با موفقیت ذخیره شد", Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("Touch Teach", fault.toString());
            }
        });
    }

    public void save(Context context){
        Class.save(this, context);
    }

}
