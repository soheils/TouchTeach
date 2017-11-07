package com.touchteach.touchteach;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.touchteach.touchteach.Adapters.ClassManagerAdapter;

public class ClassManagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.class_manager_vp);
        viewPager.setAdapter(new ClassManagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.class_manager_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
