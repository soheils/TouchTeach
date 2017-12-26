package com.touchteach.touchteach;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.touchteach.touchteach.tools.Class;

public class EditClassActivity extends AppCompatActivity {
    private Class mClass;
    private TextView mCapacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_class);
        mClass = Class.intentLoad(getIntent());
        setTitle(mClass.getTitle());

        setingView();
        bindingView();
    }

    @SuppressLint("SetTextI18n")
    private void bindingView(){
        mCapacity.setText(mClass.getCapacity() + "");
    }

    private void setingView(){
        mCapacity = (TextView) findViewById(R.id.detail_class_tv_capacity);
    }
}
