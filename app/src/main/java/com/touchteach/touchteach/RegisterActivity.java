package com.touchteach.touchteach;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.touchteach.touchteach.tools.Users;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class RegisterActivity extends AppCompatActivity {
    private String lname,fname,gender,birthday,email,password,mellicode;
    private Button register;
    private void assign(){
        lname =  findViewById(R.id.lname).toString();
        fname = findViewById(R.id.fname).toString();
        gender = findViewById(R.id.gender).toString();
        birthday = findViewById(R.id.bday).toString();
        email =findViewById(R.id.email).toString();
        password = findViewById(R.id.password).toString();
        mellicode = findViewById(R.id.mellicode).toString();
        register = findViewById(R.id.register);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        assign();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}