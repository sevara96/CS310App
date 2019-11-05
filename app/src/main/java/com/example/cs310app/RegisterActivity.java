package com.example.cs310app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private Button register;
    private EditText userEmail, userPassword, confirm;
    private FirebaseAuth mAuth;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        register = findViewById(R.id.register);
        userEmail = findViewById(R.id.username);
        userPassword = findViewById(R.id.password);
        confirm = findViewById(R.id.confirmPassword);

        loading = new ProgressDialog(this);
        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currUser = mAuth.getCurrentUser();

        if(currUser != null){

            sendUserToMain();
        }
    }
    private void sendUserToMain() {

        Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void createNewAccount() {

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        String confirmPass =confirm.getText().toString();

        int emailDomainStart = email.lastIndexOf('@');
        String emailDomain = email.substring(emailDomainStart + 1);



        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please write your email...",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your password...",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(confirmPass)){
            Toast.makeText(this, "Please confirm your password...",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmPass)){
            Toast.makeText(this, "Passwords don't match...",Toast.LENGTH_SHORT).show();

        }else if(!emailDomain.equals("usc.edu")) {
            Toast.makeText(this, "Please use a USC email to sign up...", Toast.LENGTH_SHORT).show();
        }
        else{

            loading.setTitle("Creating new account");
            loading.setMessage("Please wait");
            loading.show();
            loading.setCanceledOnTouchOutside(true);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        SendToSetUp();
                        Toast.makeText(RegisterActivity.this, "You are authenticated successfully", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    } else{
                        String message = task.getException().getMessage();
                        Toast.makeText(RegisterActivity.this, "Error occurred: " + message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }


                }
            });

        }
    }


    private void SendToSetUp() {

        Intent mainIntent = new Intent(RegisterActivity.this,SetupActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}
