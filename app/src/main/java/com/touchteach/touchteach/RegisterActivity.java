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
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.touchteach.touchteach.tools.Users;
import com.touchteach.touchteach.LoginActivity;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.lang.reflect.Array;

public class RegisterActivity extends AppCompatActivity {
    private void loginUserAsync(String email, String password)
    {
        AsyncCallback<BackendlessUser> callback = new AsyncCallback<BackendlessUser>()
        {
            @Override
            public void handleResponse( BackendlessUser loggedInUser )
            {
                startActivity(new Intent(getApplicationContext(),DashBoard.class));
            }

            @Override
            public void handleFault( BackendlessFault backendlessFault )
            {
                Toast.makeText(getApplicationContext(),"لاگین موفق نبود، لطفا اینترنت خود را بررسی کرده و دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
            }
        };

        Backendless.UserService.login( email, password , callback, true );
    }
    private String lname,fname,gender,birthday,email,password,mellicode;
    private Button register;
    private BackendlessUser currentuser;
    private ProgressBar bar;
    public String id;
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
        //TODO delete toast



        fname = ((EditText) findViewById(R.id.register_et_name)).getText().toString();
        if(fname.isEmpty()){
            Toast.makeText(this,"First Name cannot be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        lname = ((EditText) findViewById(R.id.register_et_last_name)).getText().toString();
        if(lname.isEmpty()) {
            Toast.makeText(this, "Last Name cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        int handle = ((RadioGroup) findViewById(R.id.register_rg_gender)).getCheckedRadioButtonId();
        if(handle == R.id.register_rb_male)
            gender = "male";
        else if(handle == R.id.register_rb_female)
            gender = "female";
        else{
            Toast.makeText(this,"gender cannot be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        birthday = ((EditText) findViewById(R.id.register_et_birthday)).getText().toString();
        if(birthday.isEmpty()) {
            Toast.makeText(this, "Birthday cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        email =((EditText) findViewById(R.id.register_et_email)).getText().toString();
        if(email.isEmpty()) {
            Toast.makeText(this, "email cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        password = ((EditText) findViewById(R.id.register_et_password)).getText().toString();
        if(password.isEmpty() || password.length() < 8){
            Toast.makeText(this,"Password is too short",Toast.LENGTH_SHORT).show();
            return false;
        }

        mellicode = ((EditText) findViewById(R.id.register_et_mellicode)).getText().toString();
        if(mellicode.isEmpty()) {
            Toast.makeText(this, "Mellicode cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        bar = (ProgressBar) findViewById(R.id.register_pb_loading);
        return true;
    }
    private void register(){
        currentuser = new BackendlessUser();
        currentuser.setEmail(email);
        currentuser.setPassword(password);
        currentuser.setProperty("fname",fname);
        currentuser.setProperty("lname",lname);
        currentuser.setProperty("gender",gender);
        currentuser.setProperty("bday",birthday);
        int[] array = new int[3];
        array[1] = 2;
        currentuser.setProperty("array",array);
        user = new Users(currentuser);
        Backendless.UserService.register(currentuser, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                bar.setVisibility(View.INVISIBLE);
                loginUserAsync(email,password);
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                bar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"Registration unsuccessful, please check your network",Toast.LENGTH_SHORT).show();
            }
        });
    }



}