package com.punitexample.collection_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Settings extends AppCompatActivity {

    Home home;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        logout = (Button) findViewById(R.id.logOut);
        home = new Home();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.logout();
                home.finish = true;
                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
               /* startActivity(new Intent(Settings.this, MainActivity.class));
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("CLOSE_ALL");*/
              /*  BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        // close activity
                    }
                };*/
               // registerReceiver(broadcastReceiver, intentFilter);

                finish();
            }
        });
    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finish();
       *//* Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);*//*
    }*/
}
