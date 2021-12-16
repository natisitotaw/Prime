package com.example.prime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.EditText;

public class Sign_Up extends AppCompatActivity {

    String phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText PhoneNo = findViewById(R.id.regPhoneNo);
        phoneNo = PhoneNo.getText().toString();
    }

    public void Register_User(View view) {
        Intent intent = new Intent(getApplicationContext(), VerifyPhoneNo.class);
        intent.putExtra("PhoneNo", phoneNo);
        startActivity(intent);
    }
}