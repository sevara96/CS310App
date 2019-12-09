package com.example.cs310app;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendRequestActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String currentUserID;
    private DatabaseReference friendRequests;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        mAuth =FirebaseAuth.getInstance();
        friendRequests = FirebaseDatabase.getInstance().getReference().child("Requests");
        currentUserID = mAuth.getCurrentUser().getUid();
        table = findViewById(R.id.friendRequestTable);
        final ArrayList<String> items = new ArrayList<>();

        friendRequests.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    Query tempReference = friendRequests.child(child.getKey()).orderByChild("request_type").equalTo("sent");
                    //Log.d("request: ", child.getKey());
                    tempReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot insideChild: dataSnapshot.getChildren()) {
                                //Log.d("InsideRequest: ", dataSnapshot.getValue().toString());
                                String[] tempValues = dataSnapshot.getValue().toString().split("=");
                                String tempID = tempValues[0].substring(1);
                                Log.d("tempValues of 0: ", tempID);
                                if(tempID.equals(currentUserID)) {
                                    Log.d("Sent Keys:", dataSnapshot.getKey());
                                    Query tempReference2 = FirebaseDatabase.getInstance().getReference().child("Users").child(dataSnapshot.getKey()).child("fullName");
                                    tempReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String userName = dataSnapshot.getValue().toString();
                                            TableRow tr = new TableRow(FriendRequestActivity.this);
                                            TextView name = new TextView(FriendRequestActivity.this);
                                            name.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                                            name.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                                            name.setTypeface(null, Typeface.BOLD);
                                            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                            tr.setLayoutParams(lp);
                                            name.append(userName);
                                            tr.addView(name);
                                            table.addView(tr);
                                            items.add(userName);
                                            Log.d("User Name:", userName);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void toProfilePage(View view){
        Intent intent = new Intent(FriendRequestActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
