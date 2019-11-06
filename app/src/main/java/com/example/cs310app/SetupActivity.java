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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SetupActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText phone;
    private Button save;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private ProgressDialog loading;
    String currId;
//    private StorageReference UserProfileImageRef;
//    final static int Gallery_Pick = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        mAuth = FirebaseAuth.getInstance();
        currId = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currId);
        loading = new ProgressDialog(this);
        //UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");


        name = findViewById(R.id.fullName);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        save = findViewById((R.id.save));
        //ProfileImage = findViewById(R.id.image);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveInfo();

            }
        });

//        ProfileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent galleryIntent = new Intent();
//                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//                galleryIntent.setType("image/*");
//                startActivityForResult(galleryIntent, Gallery_Pick);
//            }
//        });


//        userRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if(dataSnapshot.exists())
//                {
//                    if (dataSnapshot.hasChild("profileImage"))
//                    {
//                        String image = dataSnapshot.child("profileImage").getValue().toString();
//                        //Picasso.with(SetupActivity.this).load(image).into(ProfileImage);
//                    }
//                    else
//                    {
//                        Toast.makeText(SetupActivity.this, "Please select profile image first.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==Gallery_Pick && resultCode==RESULT_OK && data!=null)
//        {
//            Uri ImageUri = data.getData();
//
//            CropImage.activity()
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .setAspectRatio(1, 1)
//                    .start(this);
//        }
//
//        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
//        {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//
//            if(resultCode == RESULT_OK)
//            {
//                loading.setTitle("Profile Image");
//                loading.setMessage("Please wait, while we updating your profile image...");
//                loading.show();
//                loading.setCanceledOnTouchOutside(true);
//
//                Uri resultUri = result.getUri();
//
//                StorageReference filePath = UserProfileImageRef.child(currId + ".jpg");
//
//                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull final Task<UploadTask.TaskSnapshot> task)
//                    {
//                        if(task.isSuccessful())
//                        {
//                            Toast.makeText(SetupActivity.this, "Profile Image stored successfully to Firebase storage...", Toast.LENGTH_SHORT).show();
//
//                            final String downloadUrl = task.getResult().getMetadata().getReference().getDownloadUrl().toString();
//
//                            userRef.child("profileImage").setValue(downloadUrl)
//                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task)
//                                        {
//                                            if(task.isSuccessful())
//                                            {
//                                                Intent selfIntent = new Intent(SetupActivity.this, SetupActivity.class);
//                                                startActivity(selfIntent);
//
//                                                Toast.makeText(SetupActivity.this, "Profile Image stored to Firebase Database Successfully...", Toast.LENGTH_SHORT).show();
//                                                loading.dismiss();
//                                            }
//                                            else
//                                            {
//                                                String message = task.getException().getMessage();
//                                                Toast.makeText(SetupActivity.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
//                                                loading.dismiss();
//                                            }
//                                        }
//                                    });
//                        }
//                    }
//                });
//            }
//            else
//            {
//                Toast.makeText(this, "Error Occured: Image can not be cropped. Try Again.", Toast.LENGTH_SHORT).show();
//                loading.dismiss();
//            }
//        }
//    }
//
//

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
