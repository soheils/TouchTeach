package com.touchteach.touchteach;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.touchteach.touchteach.coustomViews.dialogs.InputTextDialog;
import com.touchteach.touchteach.coustomViews.dialogs.PersianDatePickerDialog;
import com.touchteach.touchteach.databinding.ActivityEditProfileBinding;
import com.touchteach.touchteach.tools.Users;

public class EditProfile extends AppCompatActivity {
    private ActivityEditProfileBinding viewBinding;
    private Users userEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        userEdit = Users.sharePreferenceLoad(this);
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);

        bindingViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_profile_action_save:
                updateUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindingViews(){
        Users user = Users.sharePreferenceLoad(this);
        //todo complete it
        viewBinding.editProfileTvName.setText(user.getFirstName());
        viewBinding.editProfileTvLastName.setText(user.getLastName());
        viewBinding.editProfileTvEmail.setText(user.getEmail());
        viewBinding.editProfileTvBirthDay.setText(user.getBirthDay());
        viewBinding.editProfileTvBio.setText(user.getBio());
    }

    public void logoutClick(View view){
        viewBinding.editProfilePb.setVisibility(View.VISIBLE);
        Users.sharePreferenceLoad(this).logout(this, new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                //todo finish all activities
                startActivity(new Intent(EditProfile.this, MainActivity.class));
                EditProfile.this.finish();
                viewBinding.editProfilePb.setVisibility(View.INVISIBLE);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                //todo handle it
            }
        });
    }

    public void editProfileProperty(View view){
        InputTextDialog.OnTextSetListener setListener = null;
        String title = "";

        switch (view.getId()){
            case R.id.edit_profile_vg_first_name:
                title = "نام";
                setListener = new InputTextDialog.OnTextSetListener() {
                    @Override
                    public void onTextSet(EditText editText, String text) {
                        viewBinding.editProfileTvName.setText(text);
                    }
                };
                break;
            case R.id.edit_profile_vg_last_name:
                title = "نام خانوادگی";
                setListener = new InputTextDialog.OnTextSetListener() {
                    @Override
                    public void onTextSet(EditText editText, String text) {
                        viewBinding.editProfileTvLastName.setText(text);
                    }
                };
                break;
            //todo complete it
        }

        InputTextDialog dialog = new InputTextDialog(this, setListener);
        dialog.setTitle(title);
        dialog.show();
    }

    public void birthDayClick(View view){
        PersianDatePickerDialog dialog = new PersianDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                viewBinding.editProfileTvBirthDay.setText(i + "/" + i1 + "/" + i2);
            }
        });
        dialog.show();
    }

    private void updateUser(){
        //todo complete it
        userEdit.setFirstName(viewBinding.editProfileTvName.getText().toString());
        userEdit.setLastName(viewBinding.editProfileTvLastName.getText().toString());
        userEdit.setBirthDay(viewBinding.editProfileTvBirthDay.getText().toString());
        userEdit.setBio(viewBinding.editProfileTvBio.getText().toString());

        viewBinding.editProfilePb.setVisibility(View.VISIBLE);

        userEdit.update(new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                viewBinding.editProfilePb.setVisibility(View.INVISIBLE);

                Users.sharePreferenceDelete(EditProfile.this);
                userEdit.sharePreferenceSave(EditProfile.this);

                Toast.makeText(EditProfile.this,"تغییرات با موفقیت ذخیره شد", Toast.LENGTH_SHORT).show();
                EditProfile.this.finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                //todo handle it
                Log.d("TouchTeach",fault.toString());
            }
        });
    }
}
