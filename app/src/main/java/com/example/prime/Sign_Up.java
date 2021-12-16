package com.example.prime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_Up extends AppCompatActivity {

    String phoneNo;
    EditText PhoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        PhoneNo = findViewById(R.id.regPhoneNo);
        phoneNo = PhoneNo.getText().toString().trim();
    }

    public void Register_User(View view) {
        //Toast.makeText(Sign_Up.this, PhoneNo.getText().toString().trim(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), VerifyPhoneNo.class);
        intent.putExtra("PhoneNo", PhoneNo.getText().toString().trim());


        startActivity(intent);
    }
}