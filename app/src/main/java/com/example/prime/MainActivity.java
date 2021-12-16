package com.example.prime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
>>>>>>> c42eba722d0e53f915d60cad70444b38e9c435d4

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
=======
        FirebaseAuth mAuth;

    }


    public void Signin(View view) {
        Intent intent = new Intent(getApplicationContext(), VerifyPhoneNo.class);
        startActivity(intent);

>>>>>>> c42eba722d0e53f915d60cad70444b38e9c435d4
    }

    public void SignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), Sign_Up.class);
        startActivity(intent);
    }
}