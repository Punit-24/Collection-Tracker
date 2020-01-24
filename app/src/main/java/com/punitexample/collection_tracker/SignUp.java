package com.punitexample.collection_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText email;
    EditText pwd;
    EditText cpwd;
    Button submit;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.pwd);
        cpwd = (EditText) findViewById(R.id.cpwd);
        submit = (Button) findViewById(R.id.submit_btn);
        auth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pd = pwd.getText().toString();
                String cpd = cpwd.getText().toString();
                if(em.isEmpty()) {
                    email.setError("Provide Email");
                    email.requestFocus();
                }
                else if(pd.isEmpty()){
                    pwd.setError("Set your password");
                    pwd.requestFocus();
                }
                else if(cpd.isEmpty()){
                    cpwd.setError("Please Confirm Your Password");
                    cpwd.requestFocus();
                }
                else if(!cpd.matches(pd)){
                    cpwd.setError("Enter Same Password As Above.");
                    cpwd.requestFocus();
                }
                else if(em.isEmpty() && pd.isEmpty() && cpd.isEmpty()){
                    Toast.makeText(SignUp.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                }
                else if(! em.isEmpty() && !pd.isEmpty()){
                    auth.createUserWithEmailAndPassword(em,pd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Not Successfull!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(SignUp.this, "Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this,Home.class));
                                finish();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(SignUp.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
