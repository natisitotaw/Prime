package com.example.prime;

/**
 * Created by NatiSt on 1/3/2022.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseVerification extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_verification);
    }

    public void ViaEmail(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void ViaPhone(View view) {
        Intent intent = new Intent(getApplicationContext(), Sign_Up.class);
        startActivity(intent);
    }
}