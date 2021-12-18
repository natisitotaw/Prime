package com.example.prime;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNo extends AppCompatActivity {
    private static final String TAG = "PhoneAuthActivity";
    String VerificationCodeBySystem;
    Button verify_button;
    EditText PhoneNoOfUser;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerificationCodeBySystem = s;
        }
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                progressBar.setVisibility(View.VISIBLE);
                verify_Code(code);
            }
            Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Log.w(TAG, "onVerificationFailed");

            if(e instanceof FirebaseAuthInvalidCredentialsException){
                // Invalid request
            }else if( e instanceof FirebaseTooManyRequestsException){
                // The sms quota for the project has been exceeded
            }
            Toast.makeText(VerifyPhoneNo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_no);
        mAuth = FirebaseAuth.getInstance();
        verify_button = findViewById(R.id.verify_btn);
        PhoneNoOfUser = findViewById(R.id.verification_code_entered_by_user);
        progressBar = findViewById(R.id.progressBar);
        String phoneNo = getIntent().getStringExtra("PhoneNo");
        sendVerificationCodeToUser(phoneNo);
    }
    // Send Verification Sms code to the users phone
    private void sendVerificationCodeToUser(String phoneNo) {
        // [START start_phone-auth]
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNo)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        String a = (phoneNo);
        //Toast.makeText(VerifyPhoneNo.this, a, Toast.LENGTH_SHORT).show();
    }
    private void verify_Code(String codeByUser){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationCodeBySystem,codeByUser);
        signInUserByCredentials(credential);
    }

    private void signInUserByCredentials(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneNo.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Log.d(TAG, "SignWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                        }else{
                            Log.w(TAG, "signWithCredential:Failure", task.getException());
                        }
                    }
                });
    }

    public void Boom(View view) {
        String code = PhoneNoOfUser.getText().toString().trim();
        verify_Code(code);
        Toast.makeText(VerifyPhoneNo.this, "Success", Toast.LENGTH_SHORT).show();
    }
}