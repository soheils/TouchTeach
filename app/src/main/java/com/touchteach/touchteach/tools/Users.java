package com.touchteach.touchteach.tools;

import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Soheil on 8/17/2017.
 */

public class Users {
    public String email,fname,lname,id;
    public String[] subjects,relations, classes;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public String[] getRelations() {
        return relations;
    }

    public void setRelations(String[] relations) {
        this.relations = relations;
    }

    public String[] getClasses() {
        return classes;
    }

    public void setClasses(String[] classes) {
        this.classes = classes;
    }

    public Users(BackendlessUser user)
    {
        email = user.getEmail();
        fname = user.getProperty("fname").toString();
        lname = user.getProperty("lname").toString();

    };
}
