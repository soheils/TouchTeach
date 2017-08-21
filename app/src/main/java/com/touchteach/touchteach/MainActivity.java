package com.touchteach.touchteach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import java.util.HashMap;
import java.util.Map;

import com.backendless.persistence.local.UserTokenStorageFactory;
import com.touchteach.touchteach.backendless.Defaults;

public class MainActivity extends Activity {
    private Button Reg,Log;
    protected boolean handle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Reg = (Button)  findViewById(R.id.reg);
        Log = (Button)  findViewById(R.id.log);

          // user login is available, skip the login activity/login form }
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );
        String userToken = UserTokenStorageFactory.instance().getStorage().get();
        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {
                handle = response;
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
        if( handle){
            startActivity(new Intent(getApplicationContext(),DashBoard.class));
            finish();
        }


        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}

