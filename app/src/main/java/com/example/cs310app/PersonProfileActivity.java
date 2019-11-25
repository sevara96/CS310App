package com.example.cs310app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PersonProfileActivity extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private TextView phone;
    private Button SendRequest, DeclineRequest;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef, friendReqRef, friendRef;
    private  String sender_id, receiever_id, current_state, saveCurrentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profile);
        mAuth = FirebaseAuth.getInstance();
        
        Bundle test = getIntent().getExtras();
        if(test != null) {
            receiever_id= getIntent().getExtras().get("user_ref_id").toString();
            sender_id = mAuth.getCurrentUser().getUid();
            userRef = FirebaseDatabase.getInstance().getReference().child("Users");
            friendReqRef = FirebaseDatabase.getInstance().getReference().child("Requests");
            friendRef = FirebaseDatabase.getInstance().getReference().child("Friends");

            InitializeFields();
            userRef.child(receiever_id).addValueEventListener(new ValueEventListener() {
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

                        maintainButton();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            return;
        }

        DeclineRequest.setVisibility(View.INVISIBLE);
        DeclineRequest.setEnabled(false);

        current_state = "not_friends";
        if(!sender_id.equals(receiever_id)){
            SendRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SendRequest.setEnabled(false);

                    if(current_state.equals("not_friends")){
                        sendRequestToPerson();
                    }
                    if(current_state.equals("request_sent")){
                        cancelrequest();
                    }
                    if(current_state.equals("request_received")){
                        acceptRequest();
                    }
                    if(current_state.equals("friends")){
                        DeclineRequest.setVisibility(View.INVISIBLE);
                        SendRequest.setVisibility(View.INVISIBLE);
                    }
                }
            });

        } else{
            DeclineRequest.setVisibility(View.INVISIBLE);
            SendRequest.setVisibility(View.INVISIBLE);

        }
    }

    private void acceptRequest() {
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        friendRef.child(sender_id).child(receiever_id).child("date").setValue(saveCurrentDate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    friendRef.child(receiever_id).child(sender_id).child("date").setValue(saveCurrentDate).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                friendReqRef.child(sender_id).child(receiever_id).removeValue()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                friendReqRef.child(receiever_id).child(sender_id)
                                                        .removeValue()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                SendRequest.setEnabled(true);
                                                                current_state = "friends";
                                                                SendRequest.setVisibility(View.INVISIBLE);
                                                                DeclineRequest.setVisibility(View.INVISIBLE);
                                                                DeclineRequest.setEnabled(false);
                                                            }
                                                        });

                                            }
                                        });

                            }
                        }
                    });
                }
            }
        });
    }

    private void cancelrequest() {

        friendReqRef.child(sender_id).child(receiever_id).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        friendReqRef.child(receiever_id).child(sender_id)
                                .removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        SendRequest.setEnabled(true);
                                        current_state = "not_friends";
                                        SendRequest.setText("Send Friend Request");
                                        DeclineRequest.setVisibility(View.INVISIBLE);
                                        DeclineRequest.setEnabled(false);
                                    }
                                });

                    }
                });
    }

    private void maintainButton() {
        friendReqRef.child(sender_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.hasChild(receiever_id)) {
                    String request_type = dataSnapshot.child(receiever_id).child("request_type")
                            .getValue().toString();

                    if (request_type.equals("sent")) {
                        current_state = "request_sent";
                        SendRequest.setText("Cancel friend request");
                        DeclineRequest.setVisibility(View.INVISIBLE);
                        DeclineRequest.setEnabled(false);
                    } else if(request_type.equals("received")){
                        current_state = "request_received";
                        SendRequest.setText("Accept Friend Request");
                        DeclineRequest.setVisibility(View.VISIBLE);
                        DeclineRequest.setEnabled(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendRequestToPerson() {

        friendReqRef.child(sender_id).child(receiever_id).child("request_type").setValue("sent")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        friendReqRef.child(receiever_id).child(sender_id)
                                .child("request_type").setValue("received")
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        SendRequest.setEnabled(true);
                                        current_state = "request_sent";
                                        SendRequest.setText("Cancel friend request");
                                        DeclineRequest.setVisibility(View.INVISIBLE);
                                        DeclineRequest.setEnabled(false);
                                    }
                                });

                    }
                });
    }

    private void InitializeFields() {

        name = findViewById(R.id.person_fullName);
        phone = findViewById(R.id.person_phone);
        email = findViewById(R.id.person_email);
        SendRequest = findViewById(R.id.person_send_friend_req);
        DeclineRequest = findViewById(R.id.person_decline_friend_req);
    }
}
