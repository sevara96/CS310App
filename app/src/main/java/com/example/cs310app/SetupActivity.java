package com.example.cs310app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SetupActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText phone;
    private Button save;
    private ImageView image;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private ProgressDialog loading;
    String currId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        mAuth = FirebaseAuth.getInstance();
        currId = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currId);
        loading = new ProgressDialog(this);


        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        save = findViewById((R.id.save));
        image = findViewById(R.id.image);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveInfo();




            }
        });
    }

    private void sendUserToMain() {

        Intent mainIntent = new Intent(SetupActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
    private void saveInfo() {

        String fullName = name.getText().toString();
        String uPhone = phone.getText().toString();
        String userEmail = email.getText().toString();

        if(TextUtils.isEmpty(fullName)){
            Toast.makeText(this, "Please write your full name...",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(uPhone)){
            Toast.makeText(this, "Please write your phone number...",Toast.LENGTH_SHORT).show();

        }
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Please write your email...",Toast.LENGTH_SHORT).show();

        } else {

            loading.setTitle("Saving information");
            loading.setMessage("Please wait");
            loading.show();
            loading.setCanceledOnTouchOutside(true);
            HashMap userMap = new HashMap();
            userMap.put("fullName",fullName );
            userMap.put("email", userEmail);
            userMap.put("phone", uPhone);

            userRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if(task.isSuccessful()){
                        sendUserToMain();
                        Toast.makeText(SetupActivity.this, "You are authenticated successfully", Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }else{
                        String message = task.getException().getMessage();
                        Toast.makeText(SetupActivity.this, "Error occurred: " + message, Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }

                }
            });

        }
    }
}
