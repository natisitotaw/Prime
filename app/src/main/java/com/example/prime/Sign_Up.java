package com.example.prime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends AppCompatActivity {
    String phoneNo;
    EditText PhoneNo, Password, Username;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        PhoneNo = findViewById(R.id.regPhoneNo);
        Password = findViewById(R.id.userPassword);
        Username = findViewById(R.id.Username);
        phoneNo = PhoneNo.getText().toString().trim();
    }

    public void Register_User(View view) {
        //Toast.makeText(Sign_Up.this, PhoneNo.getText().toString().trim(), Toast.LENGTH_SHORT).show();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("User");
        reference.setValue("helliriro");

        Intent intent = new Intent(getApplicationContext(), VerifyPhoneNo.class);
        intent.putExtra("PhoneNo", PhoneNo.getText().toString().trim());
        startActivity(intent);

    }
}