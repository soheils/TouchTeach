package com.touchteach.touchteach.tools;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sazgar on 9/28/2017.
 */

public class Subject {
    //todo add service to auto update subjects if user is teacher
    //todo save all item in sql
    private Map property = null;

    private final static String BACKENDLESS_COLUMN_SUBJECT_TITLE = "title";
    private final static String BACKENDLESS_COLUMN_ClASS = "class";
    private final static String BACKENDLESS_COLUMN_OBJECT_ID = "objectId";
    private final static String BACKENDLESS_TABLE_NAME = "Subject";

    private static List<Map> subjects;

    public Subject(String title) {
        for (Map subject : subjects) {
            if (((String) (subject.get(BACKENDLESS_COLUMN_SUBJECT_TITLE))).equals(title)) {
                property = subject;
                break;
            }
        }
        if (property == null)
            throw new IllegalArgumentException("has'n any subject whit this title in server");
    }

    public static Subject getSubject(int index) {
        return new Subject((String) subjects.get(index).get(BACKENDLESS_COLUMN_SUBJECT_TITLE));
    }

    public static void load(final AsyncCallback<List<Map>> responder) {

        Backendless.Persistence.of(BACKENDLESS_TABLE_NAME).find(new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {
                subjects = response;
                responder.handleResponse(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                responder.handleFault(fault);
            }
        });
    }

    @Override
    public String toString() {
        return (String) property.get(BACKENDLESS_COLUMN_SUBJECT_TITLE);
    }

//    public static String[] getAllSubjectsTitle(){
//        String[] result = new String[subjects.size()];
//        for (int i=0 ; i<result.length ; i++)
//            result[i] = (String) subjects.get(i).get(BACKENDLESS_COLUMN_SUBJECT_TITLE);
//        return result;
//    }

    public Map getProperty() {
        return property;
    }

    public void addClasses(AsyncCallback<Integer> asyncCallback, Map... classes) {
        Backendless.Data.of(BACKENDLESS_TABLE_NAME).setRelation(this.getProperty(), BACKENDLESS_COLUMN_ClASS, Arrays.asList(classes), asyncCallback);
    }

    public void addClasses(Map... classes){
        addClasses(new AsyncCallback<Integer>() {
            @Override
            public void handleResponse(Integer integer) {
                //todo manage it
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                //todo manage it
            }
        }, classes);
    }


}
