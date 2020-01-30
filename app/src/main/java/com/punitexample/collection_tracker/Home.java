package com.punitexample.collection_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class Home extends AppCompatActivity {


    Button settings,scan,add;
    static boolean finish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        add = (Button) findViewById(R.id.add);
        scan = (Button) findViewById(R.id.scan);
        settings = (Button) findViewById(R.id.settings);

        if(finish){
            finish();
        }




        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,Settings.class));
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,Add.class));
            }
        });


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Entity.intent = true;
                startActivity(new Intent(Home.this, Entity.class));
            }
        });
    }

}

