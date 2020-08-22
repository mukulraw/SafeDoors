package com.technobrix.tbx.safedoors.NewGatekeeper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.technobrix.tbx.safedoors.*;
import com.technobrix.tbx.safedoors.SplashLogin.Login;

public class GateHome extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawer;

    TextView name , notifications , logout;

    Button regular , visitor;

    SharedPreferences pref;

    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_home);

        pref = getSharedPreferences("pref" , MODE_PRIVATE);
        edit = pref.edit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //drawer = (DrawerLayout) findViewById(R.id.gate_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);

        regular = (Button)findViewById(R.id.regular);
        visitor = (Button)findViewById(R.id.visitor);

        /*drawer = (DrawerLayout) findViewById(R.id.gate_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/




        name = (TextView) findViewById( R.id.name);
        notifications = (TextView) findViewById( R.id.notifications);
        logout = (TextView) findViewById( R.id.logout);


        bean b = (bean)getApplicationContext();

        name.setText(b.name);
        toolbar.setTitle(b.name);


        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(GateHome.this , RegularVisitor.class);
                startActivity(i);

            }
        });

        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(GateHome.this , NewVisitor.class);
                startActivity(i);
            }
        });



       /* notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GateHome.this , Notification.class);
                startActivity(i);
            }
        });
*/







    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gate , menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout)
        {
            Intent i = new Intent(GateHome.this , Login.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

            edit.remove("user");
            edit.remove("type");
            edit.remove("pass");
            edit.apply();

            bean b = (bean)getApplicationContext();

            b.name = "";
            b.userId = "";
            b.email = "";

            startActivity(i);
            finish();


        }


        return super.onOptionsItemSelected(item);
    }












}
