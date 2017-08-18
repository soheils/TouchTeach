package com.touchteach.touchteach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{
    private String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        (findViewById(R.id.email_sign_in_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = findViewById(R.id.email).toString();
                password = findViewById(R.id.password).toString();
                if (email.isEmpty())
                    Toast.makeText(getApplicationContext(),"ایمیل نمی تواند خالی باشد",Toast.LENGTH_SHORT);
                if(password.isEmpty() || password.length() < 9)
                    Toast.makeText(getApplicationContext(),"پسورد نمی تواند خالی باشد یا کوتاه باشد" , Toast.LENGTH_SHORT);
            }
        });
    }
}
