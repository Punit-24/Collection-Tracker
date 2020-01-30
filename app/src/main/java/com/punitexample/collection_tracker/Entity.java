package com.punitexample.collection_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Entity extends AppCompatActivity {

    public static boolean intent = true;
    public static boolean homeIntent = false;
    static public TextView result;
    FirebaseFirestore db;
    DocumentReference dref ;
    FirebaseUser user;
    FirebaseAuth auth;
    static String serial_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity);
        result = (TextView) findViewById(R.id.result);
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(intent) {
            startActivity(new Intent(Entity.this,ScanQRCode.class));
        }
        /*if(homeIntent){
            startActivity(new Intent(Entity.this,Home.class));
        }
        homeIntent = false;*/
    }

    public void items(){
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        dref = db.collection(user.getEmail()).document(serial_num);
        dref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String mdate = documentSnapshot.getString("mDate");
                    String idate = documentSnapshot.getString("iDate");
                    String item = documentSnapshot.getString("iName");
                    String sdate = documentSnapshot.getString("sDate");
                    result.setText("Item Name:"+ item +"\n"+ "Serial Number:" + serial_num +"\n"+ "Installation Date:" + idate +"\n"+ "Manufacturing Date:" + mdate +"\n"+ "Last Service Date:"+ sdate);
                }else{
                    Toast.makeText(Entity.this, "Document does not exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
