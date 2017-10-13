package com.touchteach.touchteach;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.touchteach.touchteach.tools.Users;

public class LoginActivity extends AppCompatActivity{

    private ProgressBar bar;
    private TextView tvEmail, tvPassword;
    private CheckBox cbSingIn;

    private AsyncCallback<BackendlessUser> loginAsyncCallback()
    {
        bar.setVisibility(View.VISIBLE);
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
        cbSingIn = (CheckBox) findViewById(R.id.login_cb_save_me);
        tvEmail = (TextView) findViewById(R.id.login_et_user_name);
        tvPassword = (TextView) findViewById(R.id.login_et_password);

        if (isUserAutoSingIn()){
            Users user = Users.sharePreferenceLoad(this);

            tvPassword.setText("1234567890123456780");
            tvEmail.setText(user.getEmail());
            cbSingIn.setChecked(true);

            user.login(this, loginAsyncCallback());
        }
    }

    public boolean isUserAutoSingIn(){
        //todo use user method for this job
        //because security
        return this.getSharedPreferences(Users.SHARE_PREFERENCES_NAME_TAG, Context.MODE_PRIVATE)
                .getBoolean(Users.SHARE_PREFERENCES_AUTO_SING_IN_TAG, false);
    }

    public void loginClick(View view){
        String email = tvEmail.getText().toString();
        String password = tvPassword.getText().toString();
        //todo use string resource for toast text
        if (email.isEmpty())
            Toast.makeText(getApplicationContext(),"ایمیل نمی تواند خالی باشد",Toast.LENGTH_SHORT).show();
        else if(password.isEmpty() || password.length() < 8)
            Toast.makeText(getApplicationContext(),"پسورد نمی تواند خالی باشد یا کوتاه باشد" , Toast.LENGTH_SHORT).show();

        else {
            Users user = new Users();
            user.setEmail(email);

            if (cbSingIn.isChecked()) {
                user.setPassword(password);
                user.setAutoSingIn(true);

                user.sharePreferenceSave(this);

                user.login(this, loginAsyncCallback());
            } else {
                //todo delete it or not ?
                Users.sharePreferenceDelete(this);

                user.login(this ,password, loginAsyncCallback());
            }
        }
    }
}
