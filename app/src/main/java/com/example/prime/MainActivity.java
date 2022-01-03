package com.example.prime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private TextView create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth;

    }


    public void SignIn(View view) {
        //Toast.makeText(MainActivity.this, "bhbh", Toast.LENGTH_SHORT).show();
        if(!isConnected(MainActivity.this)){
            //Toast.makeText(MainActivity.this, "Connect to internet", Toast.LENGTH_SHORT).show();
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
                .setPositiveButton("connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "You must Connect To internet", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }


    public void SignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), ChooseVerification.class);
        startActivity(intent);
    }
}