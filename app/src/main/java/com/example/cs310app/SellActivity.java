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

import java.util.HashMap;

public class SellActivity extends AppCompatActivity {

    private DatabaseReference UsersRef,PostsRef;
    private FirebaseAuth mAuth;
    private String current_user_id;
    private String name,desc,imgUrl,address;
    private double price;
    EditText itemName,Description,imgURL,Address,Price;
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
        imgURL = (EditText) findViewById(R.id.URLeditText);
        Address = (EditText) findViewById(R.id.addressEditText);
        Price = (EditText) findViewById(R.id.priceEditText);
        Button saleButton = (Button) findViewById( R.id.saleButton );
        saleButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              // check data
                                              if (checkDataEntered(itemName, imgURL, Description) == true) {
                                                  name = itemName.getText().toString();
                                                  desc = Description.getText().toString();
                                                  imgUrl = imgURL.getText().toString();
                                                  address = Address.getText().toString();
                                                  String sPrice = Price.getText().toString();
                                                  price = Double.parseDouble(sPrice);
                                                  //save information to database​
                                                  SavingPostInformationToDatabase();
                                              }
                                          }
                                      });
        //  when logout button click
        Button LogOut = (Button) findViewById( R.id.logoutButton );
        LogOut.setOnClickListener( new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           // code
                                           startActivity(new android.content.Intent(getApplicationContext(), LoginActivity.class));
                                       }
                                   });
        // when Buy button click
        Button Buy = (Button) findViewById( R.id.buyButton );
        Buy.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change to BuyActivity page
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

                    HashMap postsMap = new HashMap();
                    postsMap.put("uid",current_user_id );
                    postsMap.put("name",name );
                    postsMap.put("description",desc );
                    postsMap.put("imageURL",imgUrl );
                    postsMap.put("address",address );
                    postsMap.put("price",price );
                    postsMap.put("fullname",userFullName );
                    PostsRef.child(current_user_id+"Items").updateChildren(postsMap).addOnCompleteListener(new OnCompleteListener() {
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

    boolean checkDataEntered(EditText itemName,EditText imgURL,EditText Description){
        if (isEmpty(itemName)) {
            Toast t =Toast.makeText(this,"You must enter name of the item to sell",Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        if (isEmpty(imgURL)) {
            imgURL.setError("Image URL is required");
            return false;
        }
        if (isEmpty(Description)) {
            Description.setError("Description is required");
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

