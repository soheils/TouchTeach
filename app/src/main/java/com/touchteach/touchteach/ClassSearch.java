package com.touchteach.touchteach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.DataQueryBuilder;
import com.touchteach.touchteach.tools.Class;
import com.touchteach.touchteach.tools.SqlLiteHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ClassSearch extends AppCompatActivity {
    public class Contact
    {
        private String objectId;
        private String name;
        private int age;
        private String phone;
        private String title;

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId( String objectId ) {
            this.objectId = objectId;
        }

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge( int age ) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone( String phone ) {
            this.phone = phone;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle( String title ) {
            this.title = title;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_search);
    }
    public void searchFunction(){
        String title = "title";
        String subject = "subject";
        String creator = "creator";
        String Clause = "title LIKE '" + title + "%' OR subject LIKE '" + subject + "%' OR creator LIKE '" + creator + "%'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause( Clause);

        Backendless.Data.of( Class.class ).find( queryBuilder, new AsyncCallback<List<Class>>() {
            @Override
            public void handleResponse(List<Class> response) {
                for (Class result : response){
                    s
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e( "MYAPP", fault.toString() );
            }
        });


    }

}
