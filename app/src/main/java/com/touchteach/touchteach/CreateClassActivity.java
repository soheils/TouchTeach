package com.touchteach.touchteach;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.touchteach.touchteach.coustomViews.PersianDatePickerDialog;
import com.touchteach.touchteach.databinding.ActivityCreateClassBinding;

import com.touchteach.touchteach.tools.Class;
import com.touchteach.touchteach.tools.Subject;

public class CreateClassActivity extends AppCompatActivity {

    private ActivityCreateClassBinding viewBinding;
    private Spinner spSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        setViews();
        bindView();
    }

    private void bindView(){
        //todo bind spinner
        Subject.load();
    }

    private void setViews(){
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_class);
        spSubjects = viewBinding.createClassSpSubject;

    }


    public void next(){
        //todo check input values is correct
        String className = viewBinding.createClassEdClassName.getText().toString();
        String capacity = viewBinding.createClassEdCapacity.getText().toString();
        String cost = viewBinding.createClassEdCost.getText().toString();
        String description = viewBinding.createClassEdDescription.getText().toString();

        Class saveClass = new Class(className);
        saveClass.setCapacity(capacity);
        saveClass.setCost(cost);
        saveClass.setDescription(description);

        Intent startActivityIntent = new Intent(this, CreateClassTimeTableActivity.class);
        saveClass.intentSave(startActivityIntent);


        startActivity(startActivityIntent);
    }


    public void clickSetDate(final View view){
        if (view instanceof TextView) {
            new PersianDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    ((TextView) view).setText(i + "/" + i1 + "/" + i2);
                    //todo set max & min date
                }
            }).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.next_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_next:
                next();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
