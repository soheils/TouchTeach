package com.touchteach.touchteach.tools;

import android.app.Application;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.backendless.BackendlessUser;
import com.touchteach.touchteach.ClassSearch;
import com.touchteach.touchteach.DashBoard;
import com.touchteach.touchteach.EditProfile;
import com.touchteach.touchteach.RequestClasses;

import static android.R.attr.id;

/**
 * Created by Soheil on 8/21/2017.
 */

public class SliderMethods extends AppCompatActivity {
    public NavigationView navigationView;
    public Users user;
    public SliderMethods(NavigationView navigationView, Users user){
        this.navigationView = navigationView;
        this.user = user;
    }
    public void setName(int id){
        TextView view =(TextView) navigationView.getHeaderView(0).findViewById(id);
        view.setText(user.getFname() + " " + user.getLname());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EditProfile.class));
            }
        });
//    TODO khob chetor be baghie item haye slider dastrasi dashte basham?
    }
    public void setHome(int id){
        MenuItem item =  navigationView.getHeaderView(1).findViewById(id);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(),DashBoard.class));
                return true;
            }
        });
    }


    public void setAllClasses(int id){
        MenuItem item =  navigationView.findViewById(id);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(),ClassSearch.class));
                return true;
            }
        });
    }
    public void setRequestClasses(int id){
        MenuItem item =  navigationView.findViewById(id);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(),RequestClasses.class));
                return true;
            }
        });
    }
    public void ExecuteSetterMethods(int... ids){
        setName(ids[0]);
        setHome(ids[1]);
        setAllClasses(ids[2]);
        setRequestClasses(ids[3]);


    }
}
