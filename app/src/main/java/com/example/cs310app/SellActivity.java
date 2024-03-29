package com.example.cs310app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class SellActivity extends AppCompatActivity {

    private DatabaseReference UsersRef,PostsRef;
    private FirebaseAuth mAuth;
    private String current_user_id;
    private String name,desc,address,category,tag;
    private String saveCurrentTime,saveCurrentDate,postRandomName;
    private double price;
    EditText itemName,Description,Address,Price,Category, Tag;
    //private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_sellactivity);
        mAuth = FirebaseAuth.getInstance();
        current_user_id= mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        PostsRef = FirebaseDatabase.getInstance().getReference().child("Items");

//        navigationView = findViewById(R.id.navigation);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//                UserMenu(menuItem);
//                return false;
//            }
//        });



        itemName = (EditText) findViewById(R.id.itemNameEditText);
        Description = (EditText) findViewById(R.id.descEditText);
        Address = (EditText) findViewById(R.id.addressEditText);
        Price = (EditText) findViewById(R.id.priceEditText);
        Category = (EditText) findViewById(R.id.categoryEditText);
        Tag = (EditText) findViewById(R.id.tagEditText);
        Button saleButton = (Button) findViewById( R.id.saleButton );
        saleButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              // check data
                                              if (checkDataEntered(itemName, Description) == true) {
                                                  name = itemName.getText().toString();
                                                  desc = Description.getText().toString();
                                                  address = Address.getText().toString();
                                                  String sPrice = Price.getText().toString();
                                                  price = Double.parseDouble(sPrice);
                                                  category=Category.getText().toString();
                                                  tag=Tag.getText().toString();
                                                  //save information to database​
                                                  SavingPostInformationToDatabase();
                                              }
                                          }
                                      });
        // when Buy button click
        Button Buy = (Button) findViewById( R.id.buyButton );
        Buy.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new android.content.Intent( getApplicationContext(), MainActivity.class ) );
            }
        } );
        // when Profile button click
        Button Profile = (Button) findViewById( R.id.profileButton );
        Profile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new android.content.Intent( getApplicationContext(), ProfileActivity.class ) );
            }
        } );
    }
    //--------------------------
    private void SavingPostInformationToDatabase(){
        UsersRef.child(current_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String userFullName = dataSnapshot.child("fullName").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();///////////////////
                    String phone = dataSnapshot.child("phone").getValue().toString();///////////////////


                    HashMap postsMap = new HashMap();
                    postsMap.put("uid",current_user_id );
                    postsMap.put("title",name );
                    postsMap.put("description",desc );
                    postsMap.put("address",address );
                    postsMap.put("price",price );
                    postsMap.put("category",category);
                    postsMap.put("tag",tag);
                    postsMap.put("fullName",userFullName);///////////////////////////
                    postsMap.put("email",email);/////////////////////////////////
                    postsMap.put("phone",phone);////////////////////////////

                    Calendar calFordDate = Calendar.getInstance();////////////////////////////
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");////////////////////////////
                    saveCurrentDate = currentDate.format(calFordDate.getTime());////////////////////////////

                    Calendar calFordTime = Calendar.getInstance();////////////////////////////
                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");////////////////////////////
                    saveCurrentTime = currentTime.format(calFordDate.getTime());////////////////////////////

                    postRandomName = saveCurrentDate + saveCurrentTime;////////////////////////////
                    ////////////////////////////under
                    PostsRef.child(current_user_id+postRandomName).updateChildren(postsMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(Task task) {
                            if(task.isSuccessful()){
                                //go to account page
                                sendUserToAccountPage();
                                Toast.makeText(SellActivity.this, "Item is added to the database successfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(SellActivity.this, "Error Occurred while updating your item.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void sendUserToAccountPage(){
        Intent mainIntent = new Intent(SellActivity.this, ProfileActivity.class);
        startActivity(mainIntent);
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered(EditText itemName,EditText Description){
        if (isEmpty(itemName)) {
            Toast t =Toast.makeText(this,"You must enter name of the item to sell",Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (isEmpty(Description)) {
            //  Description.setError("Description is required");
            Toast t =Toast.makeText(this,"You must enter description of the item to sell",Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        return true;
    }

    private void UserMenu(MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(SellActivity.this, "Profile " , Toast.LENGTH_SHORT).show();
                break;
            case R.id.buy:
                Toast.makeText(SellActivity.this, "Buy " , Toast.LENGTH_SHORT).show();
                break;
            case R.id.sell:
                Toast.makeText(SellActivity.this, "Sell " , Toast.LENGTH_SHORT).show();
                toSellActivity();
                break;

            case R.id.FindFriends:
                Toast.makeText(SellActivity.this, "Find Friends " , Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                mAuth.signOut();
                toLogActivity();
                break;
        }

    }

    private void toLogActivity() {
        Intent loginIntent = new Intent( SellActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }

    private void toSellActivity() {

        Intent setupIntent = new Intent( SellActivity.this, SellActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();

    }
}

