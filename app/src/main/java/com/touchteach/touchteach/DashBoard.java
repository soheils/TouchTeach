package com.touchteach.touchteach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.touchteach.touchteach.coustomViews.CodeLibrary;

public class DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    //todo moratab shavad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClassSearch.class);
                startActivity(intent);
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.dash_board_drower_lay);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.dash_board_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        String currentUserObjectId = Backendless.UserService.loggedInUser();
//        Backendless.UserService.findById(currentUserObjectId, new AsyncCallback<BackendlessUser>() {
//            @Override
//            public void handleResponse(BackendlessUser response) {
//                Backendless.UserService.setCurrentUser( response );
//            }
//
//            @Override
//            public void handleFault(BackendlessFault fault) {
//
//            }
//        });
//        NavigationView view = ((NavigationView) findViewById(R.id.nav_view));
//        SliderMethods methods = new SliderMethods(view,StoredUser);
//        methods.ExecuteSetterMethods(R.id.slide_tv_user_name,R.id.slide_item_home,R.id.slide_item_class_group,R.id.activity_request_classes);
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case CodeLibrary.CLOSE_PARENT_ACTIVITY:
                finish();
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id){
            case R.id.slide_item_create_class:
                intent = new Intent(this, CreateClassActivity.class);
                startActivity(intent);
                break;
            case R.id.slide_item_my_classes:
                intent = new Intent(this, ClassManagerActivity.class);
                startActivity(intent);
                break;

        }
        // Handle drawe view item clicks here.
        //TODO handle slider
        return true;
    }

    public void profile(View view){
        startActivityForResult(new Intent(this, EditProfile.class), 0);
    }
}
