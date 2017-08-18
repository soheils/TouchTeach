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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.touchteach.touchteach.tools.Users;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class RegisterActivity extends AppCompatActivity {
    private String lname,fname,gender,birthday,email,password,mellicode;
    private Button register;
    private BackendlessUser currentuser;
    private ProgressBar bar;
    private Users user;

    public BackendlessUser getCurrentuser() {
        return currentuser;
    }

    public Users getUser() {
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button) findViewById(R.id.register_bt_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(assignAndCheck()) {
                    bar.setVisibility(View.VISIBLE);
                    register();
                }

            }
        });

    }
    private boolean assignAndCheck(){
        //TODO all messages must be in Persian
        lname = ((EditText) findViewById(R.id.register_et_last_name)).getText().toString();
        if(lname.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Last Name cannot be empty", Toast.LENGTH_SHORT);
            return false;
        }
        fname = ((EditText) findViewById(R.id.register_et_name)).getText().toString();
        if(fname.isEmpty()){
            Toast.makeText(getApplicationContext(),"First Name cannot be empty",Toast.LENGTH_SHORT);
            return false;
        }
       int handle = ((RadioGroup) findViewById(R.id.register_rg_gender)).getCheckedRadioButtonId();
        if(handle == R.id.register_rb_male)
            gender = "male";
        else
            gender = "female";

        if(gender.isEmpty()){
            Toast.makeText(getApplicationContext(),"gender cannot be empty",Toast.LENGTH_SHORT);
            return false;
        }
        birthday = ((EditText) findViewById(R.id.register_et_birthday)).getText().toString();
        if(birthday.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Birthday cannot be empty", Toast.LENGTH_SHORT);
            return false;
        }
        email =((EditText) findViewById(R.id.register_et_email)).getText().toString();
        if(email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "email cannot be empty", Toast.LENGTH_SHORT);
            return false;
        }
        password = ((EditText) findViewById(R.id.register_et_password)).getText().toString();
        if(password.isEmpty() || password.length() < 9){
            Toast.makeText(getApplicationContext(),"Password is too short",Toast.LENGTH_SHORT);
            return false;
        }
        mellicode = ((EditText) findViewById(R.id.register_et_mellicode)).getText().toString();
        if(mellicode.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Mellicode cannot be empty", Toast.LENGTH_SHORT);
            return false;
        }
        bar = (ProgressBar) findViewById(R.id.register_pb_loading);
        return true;
    };
    private void register(){
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
                Toast.makeText(getApplicationContext(),"Registration unsuccessful, please check your network",Toast.LENGTH_SHORT);
            }
        });
    }

}