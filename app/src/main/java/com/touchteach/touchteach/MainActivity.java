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
        Reg =  findViewById(R.id.reg);
        Log =  findViewById(R.id.log);
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );
        if (Backendless.UserService.CurrentUser().getUserId() != null) {
            startActivity(new Intent(getApplicationContext(),DashBoard.class));
        }
        HashMap testObject = new HashMap<>();
        testObject.put( "foo", "bar" );
        Backendless.Data.of( "TestTable" ).save(testObject, new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {
                TextView label = new TextView(MainActivity.this);
                label.setText("Object is saved in Backendless. Please check in the console.");
                setContentView(label);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
            }
        });

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

