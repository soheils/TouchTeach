package com.touchteach.touchteach;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.touchteach.touchteach.tools.Users;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class RegisterActivity extends AppCompatActivity {
    private String lname,fname,gender,birthday,email,password,mellicode;
    private Button register;
    public BackendlessUser currentuser;
    private ProgressBar bar;
    public Users user;
    private void assignAndCheck(){
        //TODO all messages must be in Persian
        lname =  findViewById(R.id.lname).toString();
        if(lname.isEmpty())
            Toast.makeText(getApplicationContext(),"Last Name cannot be empty",Toast.LENGTH_SHORT);
        fname = findViewById(R.id.fname).toString();
        if(fname.isEmpty())
            Toast.makeText(getApplicationContext(),"First Name cannot be empty",Toast.LENGTH_SHORT);
        gender = findViewById(R.id.gender).toString();
        if(gender.isEmpty())
            Toast.makeText(getApplicationContext(),"gender cannot be empty",Toast.LENGTH_SHORT);
        birthday = findViewById(R.id.bday).toString();
        if(birthday.isEmpty())
            Toast.makeText(getApplicationContext(),"Birthday cannot be empty",Toast.LENGTH_SHORT);
        email =findViewById(R.id.email).toString();
        if(email.isEmpty())
            Toast.makeText(getApplicationContext(),"email cannot be empty",Toast.LENGTH_SHORT);
        password = findViewById(R.id.password).toString();
        if(password.isEmpty() || password.length() < 9)
            Toast.makeText(getApplicationContext(),"Password is too short",Toast.LENGTH_SHORT);
        mellicode = findViewById(R.id.mellicode).toString();
        if(mellicode.isEmpty())
            Toast.makeText(getApplicationContext(),"Mellicode cannot be empty",Toast.LENGTH_SHORT);
        register = findViewById(R.id.register);
        bar = findViewById(R.id.bar);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.setVisibility(View.VISIBLE);
                assignAndCheck();
                currentuser = new BackendlessUser();
                currentuser.setEmail(email);
                currentuser.setPassword(password);
                currentuser.setProperty("fname",fname);
                currentuser.setProperty("lname",lname);
                currentuser.setProperty("gender",gender);
                currentuser.setProperty("bday",birthday);
                currentuser.setProperty("mellicode",mellicode);
                user = new Users(currentuser);
                Backendless.UserService.register(currentuser, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        bar.setVisibility(View.INVISIBLE);
                        startActivity(new Intent(getApplicationContext(),DashBoard.class));
                        finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        bar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Registration unsuccessful, please check your network")
                    }
                });
            }
        });

    }

}