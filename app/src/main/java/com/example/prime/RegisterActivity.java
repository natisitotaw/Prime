package com.example.prime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prime.Database.Userdb;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    EditText username,email,password,phone;
    Button register;
    FirebaseAuth auth;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.username);
        email =findViewById(R.id.email);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        register=findViewById(R.id.register);
        auth =FirebaseAuth.getInstance();



        register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("User");


                String txt_email = email.getText().toString();
                String txt_phone =phone.getText().toString();
                String txt_username = username.getText().toString();
                String txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(RegisterActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                }else if(txt_password.length() < 6){
                    Toast.makeText(RegisterActivity.this, "password is too small", Toast.LENGTH_SHORT).show();
                }else{

                    registerUser(txt_email , txt_password);
                }
            }
        });
    }

    private void registerUser(String emails, String passwords) {
        auth.createUserWithEmailAndPassword(emails, passwords).addOnCompleteListener(RegisterActivity.this ,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //Toast.makeText(RegisterActivity.this, "Registered sucessful. please check your email for verification", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), VerifyEmail.class);
                                intent.putExtra("phoneno", phone.getText().toString().trim());
                                intent.putExtra("username", username.getText().toString().trim());
                                intent.putExtra("password",password.getText().toString().trim());
                                intent.putExtra("email",email.getText().toString().trim());
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
                }


            }
        });
    }


}




