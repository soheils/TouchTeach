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
    public Users(BackendlessUser user)
    {
        email = user.getEmail();
        fname = user.getProperty("fname").toString();
    };
}
