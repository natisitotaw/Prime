package com.example.prime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.prime.Database.Userdb;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VerifyEmail extends AppCompatActivity {
    Button button;
    FirebaseAuth auth;
    String phoneNo, userName, passWord, Email;
    FirebaseDatabase rootnode;
    int verify = 0;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        button = findViewById(R.id.verify_Email);
        auth=FirebaseAuth.getInstance();
        phoneNo = getIntent().getStringExtra("phoneno");
        userName = getIntent().getStringExtra("username");
        passWord = getIntent().getStringExtra("password");
        Email = getIntent().getStringExtra("email");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser(Email,passWord);


            }
        });
    }
        private void loginUser(String Email , String passWord) {
            auth.signInWithEmailAndPassword(Email , passWord).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(@NonNull AuthResult authResult) {
                    if(auth.getCurrentUser().isEmailVerified()){
                        rootnode = FirebaseDatabase.getInstance();
                        reference =rootnode.getReference("User");
                        Userdb userdb = new Userdb(userName, phoneNo, passWord);
                        reference.child(phoneNo).setValue(userdb);
                        Toast.makeText(VerifyEmail.this, "Verify Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomePage.class));
                        finish();
                    }
                    else{
                        Toast.makeText(VerifyEmail.this, "please verify your email address", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }


