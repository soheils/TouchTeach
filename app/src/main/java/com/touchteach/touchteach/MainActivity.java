package com.touchteach.touchteach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import com.touchteach.touchteach.backendless.Defaults;
import com.touchteach.touchteach.coustomViews.CodeLibrary;
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

        //todo think twice load form share preference is to long
        if (Users.sharePreferenceLoad(this).isAutoSingIn()){
            login(null);
        }
    }

    public void register (View view){
        startActivityForResult(new Intent(getApplicationContext(),RegisterActivity.class), 0);
    }

    public void login(View view){
        startActivityForResult(new Intent(getApplicationContext(),LoginActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case CodeLibrary.CLOSE_PARENT_ACTIVITY:
                finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

