package com.touchteach.touchteach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.touchteach.touchteach.tools.Users;

public class LoginActivity extends AppCompatActivity{
    private String email,password;
    private ProgressBar bar;
    private AsyncCallback<BackendlessUser> loginAsyncCallback()
    {
        return new AsyncCallback<BackendlessUser>()
        {
            @Override
            public void handleResponse( BackendlessUser loggedInUser )
            {
                bar.setVisibility(View.INVISIBLE);
                LoginActivity.this.finish();
                startActivity(new Intent(getApplicationContext(),DashBoard.class));
            }

            @Override
            public void handleFault( BackendlessFault backendlessFault )
            {
                //todo correct handle
                bar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"لاگین موفق نبود، لطفا اینترنت خود را بررسی کرده و دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
            }
        };
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bar = (ProgressBar) findViewById(R.id.login_pd_loading);
    }

    public void loginClick(View view){
        email = ((EditText) findViewById(R.id.login_et_user_name)).getText().toString();
        password = ((EditText)findViewById(R.id.login_et_password)).getText().toString();
        if (email.isEmpty())
            Toast.makeText(getApplicationContext(),"ایمیل نمی تواند خالی باشد",Toast.LENGTH_SHORT).show();
        else if(password.isEmpty() || password.length() < 8)
            Toast.makeText(getApplicationContext(),"پسورد نمی تواند خالی باشد یا کوتاه باشد" , Toast.LENGTH_SHORT).show();

        else {
            bar.setVisibility(View.VISIBLE);
            if (((CheckBox) findViewById(R.id.login_cb_save_me)).isChecked()) {
                //todo complete
                Users user = new Users();
                user.setEmail(email);
                user.setPassword(password);
                //todo save user in share preferences
                user.login(loginAsyncCallback());
            } else {
                Users.login(email, password, loginAsyncCallback());
            }
        }
    }
}
