package com.example.prime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private TextView create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        create = findViewById(R.id.create);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth;
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this ,Sign_Up.class));
                finish();
            }
        });

    }


    public void Signin(View view) {
        Intent intent = new Intent(getApplicationContext(), VerifyPhoneNo.class);
        startActivity(intent);

    }

    public void SignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), Sign_Up.class);
        startActivity(intent);
    }
}