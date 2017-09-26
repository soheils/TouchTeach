package com.touchteach.touchteach;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.touchteach.touchteach.databinding.ActivityCreateClassBinding;
import com.touchteach.touchteach.tools.Class;

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
    }

    private void setViews(){
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_class);

        spSubjects = viewBinding.createClassSpSubject;

    }

    public void saveClass(View view){
        String className = viewBinding.createClassEdClassName.getText().toString();

        String capacityString = viewBinding.createClassEdCapacity.getText().toString();
        int capacity = Integer.parseInt(capacityString);

        String costString = viewBinding.createClassEdCost.getText().toString();
        int cost = Integer.parseInt(costString);


        Class createdClass = new Class(className);

        createdClass.setCapacity(capacity);
        createdClass.setCost(cost);

        createdClass.save(this);
    }
}
