package com.punitexample.collection_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Add extends AppCompatActivity {

    static EditText mDate,iDate,sNum,sDate,iName;
    DatePickerDialog datePickerDialog;
    Button submit;
    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseUser user;
    int i = 1;
    static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mDate = (EditText) findViewById(R.id.mDate);
        sDate = (EditText) findViewById(R.id.sDate);
        iDate = (EditText) findViewById(R.id.iDate);
        sNum = (EditText) findViewById(R.id.sNum);
        iName = (EditText) findViewById(R.id.iName);
        submit = (Button) findViewById(R.id.submitBtn);
        user = FirebaseAuth.getInstance().getCurrentUser();
        email= user.getEmail();


       /* mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        sDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        iDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });*/

        db = FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(iName.getText().toString(),sNum.getText().toString(),mDate.getText().toString(),
                        iDate.getText().toString(),sDate.getText().toString());
                db.collection(email).document(sNum.getText().toString()).set(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Add.this,"Submitted",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(Add.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                startActivity(new Intent(Add.this,GenerateQR.class));
            }
        });

    }
}
