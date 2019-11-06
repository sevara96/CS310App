package com.example.cs310app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {


    private TextView name;
    private TextView email;
    private TextView phone;

    private ImageView image;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private ProgressDialog loading;
    public String currId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        currId = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currId);
        name = findViewById(R.id.fullName);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        //image = findViewById(R.id.image);


        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String myName = dataSnapshot.child("fullName").getValue().toString();
                    String myEmail = dataSnapshot.child("email").getValue().toString();
                    String myPhone = dataSnapshot.child("phone").getValue().toString();
                    //String myProfileImage = dataSnapshot.child("profileImage").getValue().toString();
                    // Picasso.with(ProfileActivity.this).load(myProfileImage).into(image);


                    name.setText(myName);
                    email.setText(myEmail);
                    phone.setText(myPhone);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {




            }
        });
    }

    public void tosellpage(View view)
    {
        Intent intent = new Intent(ProfileActivity.this, SellActivity.class);
        startActivity(intent);
    }
    public void tomainactivity(View view)
    {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void tofindfriendsactivity(View view)
    {
        Intent intent = new Intent(ProfileActivity.this, FindFriendsActivity.class);
        startActivity(intent);
    }
    ///////////////////////////////
    public void toSetUpactivity(View view)
    {
        Intent intent = new Intent(ProfileActivity.this, SetupActivity.class);
        startActivity(intent);
    }
    /////////////////////////////////
    public void toLogActivity(View view) {
        mAuth.signOut();
        Intent loginIntent = new Intent( ProfileActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}
