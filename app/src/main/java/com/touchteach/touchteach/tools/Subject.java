package com.touchteach.touchteach.tools;

import android.os.AsyncTask;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;
import java.util.Map;

/**
 * Created by sazgar on 9/28/2017.
 */

public abstract class Subject {
    //todo add service to auto update subjects if user is teacher
    //todo save all item in sql
    private static String[] subjects;

    public static void load(final AsyncCallback<List<Map>> responder){

        Backendless.Persistence.of("Subject").find(new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {
                int size = response.size();
                subjects = new String[size];
                for (int i=0 ; i<size ; i++)
                    subjects[i] = (String) response.get(i).get("title");
                responder.handleResponse(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                responder.handleFault(fault);
            }
        });
    }

    public static String[] getSubjects(){
        return subjects;
    }

    public static String getSubjext(int index){
        return subjects[index];
    }

}
