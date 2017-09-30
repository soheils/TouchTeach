package com.touchteach.touchteach;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.touchteach.touchteach.coustomViews.PersianTimePickerDialog;
import com.touchteach.touchteach.tools.Class;

public class CreateClassTimeTableActivity extends AppCompatActivity {
    private Class setTimeClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_time_table);
        setTimeClass = Class.intentLoad(getIntent());
    }

    public void setTime(final View view){
        new PersianTimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if (view instanceof TextView){
                    TextView textView = (TextView) view;
                    //todo use toString after Override it
                    textView.setText(i+":"+i1);
                }
            }
        }).show();
    }
    //todo create and manage save button
}
