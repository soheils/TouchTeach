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
import com.touchteach.touchteach.backendless.Defaults;

public class MainActivity extends Activity {
    private Button Reg,Log;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Reg = (Button)  findViewById(R.id.reg);
        Log = (Button)  findViewById(R.id.log);
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );
//        if (Backendless.UserService.CurrentUser().getUserId() != null) {
//            startActivity(new Intent(getApplicationContext(),DashBoard.class));
//        }


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

