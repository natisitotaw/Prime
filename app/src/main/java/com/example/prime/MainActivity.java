package com.example.prime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;

import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prime.Database.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private TextView create;
    Button signin;
    private EditText password,phoneno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth;
        signin = (Button) findViewById(R.id.varify);
        phoneno = (EditText) findViewById(R.id.userphoneno);
        password = (EditText) findViewById(R.id.passwordentered);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(phoneno.getText().toString()).exists()){
                            mDialog.dismiss();
                            User user = snapshot.child(phoneno.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(password.getText().toString())){
                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void SignIn(View view) {

        if(!isConnected(MainActivity.this)){
            Toast.makeText(MainActivity.this, "Connect to internet", Toast.LENGTH_SHORT).show();
            showCustomDialog();
        }
    }


    //Check internet
    private boolean isConnected(MainActivity mainActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wificonn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobilecon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        if((wificonn != null && wificonn.isConnected()) || (mobilecon != null && mobilecon.isConnected())){
            return true;
        }
        else{
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("connect", (dialog, which) -> startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)))
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(MainActivity.this, "You must Connect To internet", Toast.LENGTH_SHORT).show();
                    finish();
                });
        builder.create();
    }

    public void SignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), ChooseVerification.class);
        startActivity(intent);
    }
}