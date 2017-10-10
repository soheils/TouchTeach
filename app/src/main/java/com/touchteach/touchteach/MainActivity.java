package com.touchteach.touchteach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import com.touchteach.touchteach.backendless.Defaults;
import com.touchteach.touchteach.tools.Users;

public class MainActivity extends Activity {
    private BackendlessUser currentuser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          // user login is available, skip the login activity/login form }
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );
        String currentUserObjectId = Backendless.UserService.loggedInUser();
        //todo close this activity when user login or sing up

        //todo think twice load form share preference is to long
        if (Users.sharepreferenceLoad(this).isAutoSingIn()){
            login(null);
        }
    }

    public void register (View view){
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
    }

    public void login(View view){
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }
}

