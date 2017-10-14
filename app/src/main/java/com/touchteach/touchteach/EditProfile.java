package com.touchteach.touchteach;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.touchteach.touchteach.databinding.ActivityEditProfileBinding;
import com.touchteach.touchteach.tools.Users;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

    }

    @Override
    protected void onResume() {
        super.onResume();
        bindingViews();
    }

    private void bindingViews(){
        Users user = Users.sharePreferenceLoad(this);

        ActivityEditProfileBinding viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);

        viewBinding.editProfileTvName.setText(user.getFirstName());
        viewBinding.editProfileTvLastName.setText(user.getLastName());
        viewBinding.editProfileTvEmail.setText(user.getEmail());
    }
}
