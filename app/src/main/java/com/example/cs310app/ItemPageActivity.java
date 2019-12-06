package com.example.cs310app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ItemPageActivity extends AppCompatActivity {

    TextView mTitleTv, mDescTv,mPriceTv, mLocationTv, mFullNameTv, mEmailTv, mPhoneTv;
    ImageView mImageIv;
    Button mButton;

    private FirebaseAuth mAuth;
    private String currentUserID;
    private DatabaseReference clickPostref;
    private DatabaseReference allUsersDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        ActionBar actionBar = getSupportActionBar();
        allUsersDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");

        mTitleTv = findViewById(R.id.title);
        mDescTv= findViewById(R.id.description);
        mImageIv =findViewById(R.id.imageView);
        mButton = findViewById(R.id.markSold);

        mPriceTv = findViewById(R.id.price);
        mLocationTv = findViewById(R.id.sellerAddress);
        mEmailTv = findViewById(R.id.sellerEmail);
        mPhoneTv = findViewById(R.id.sellerPhone);
        mFullNameTv = findViewById(R.id.sellerName);


        //get data from intent where we put our data
        Intent intent = getIntent();

        String mTitle = intent.getStringExtra("iTitle");
        String mDescription = "Description: "+intent.getStringExtra("iDesc");
        String mPrice = "Price: $"+intent.getStringExtra("iPrice");
        String mLocation = "Location: "+intent.getStringExtra("iLocation");
        final String mFullName ="Seller Name: "+intent.getStringExtra("iFullName");
        String mTag ="Tag: "+intent.getStringExtra("iTag");
        String mCategory ="Category: "+intent.getStringExtra("iCategory");



        /////byte[] mBytes = getIntent().getByteArrayExtra("iImage");
        //now you need to decode the image because from preious activity we get our image in bytes

        /////Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes, 0, mBytes.length);

        actionBar.setTitle(mTitle); //which title we get from previous activity that will get in our action bar

        //now get our data in our view, which we get in our previous activitiy

        mTitleTv.setText(mTitle);
        mDescTv.setText(mDescription);
        mFullNameTv.setText(Html.fromHtml("<u>"+mFullName+"</u>") );
        mPriceTv.setText(mPrice);
        mLocationTv.setText(mLocation);
        //mFullNameTv.setText(mFullName);
        mPhoneTv.setText(mTag);
        mEmailTv.setText(mCategory);

        mAuth =FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        mButton = findViewById(R.id.markSold);
        mButton.setVisibility(View.INVISIBLE);





        final Query clickPostref = FirebaseDatabase.getInstance().getReference().child("Items").orderByChild("title").equalTo(mTitle);
        final Query dataBaseUserId = FirebaseDatabase.getInstance().getReference().child("Items").orderByChild("uid").equalTo(currentUserID);



        clickPostref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {


                    dataBaseUserId.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot data) {

                            if(data.exists()){

                                mButton.setVisibility(View.VISIBLE);

                                mButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                                            appleSnapshot.getRef().removeValue();
                                        }
                                        toMainActivity();
                                    }
                                });
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

        mFullNameTv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String fullName = mFullName;
                allUsersDatabaseRef.orderByChild("fullName").equalTo(getIntent().getStringExtra("iFullName")).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        String user_ref_id = "test";

                        for(DataSnapshot child: snapshot.getChildren()) {
                            user_ref_id = child.getKey();
                        }
                        Intent intent = new Intent(ItemPageActivity.this, PersonProfileActivity.class);
                        intent.putExtra("user_ref_id", user_ref_id);
                        startActivity(intent);

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });



//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                markItemSold();
//            }
//        });



    }

    private void markItemSold(){
        clickPostref.removeValue();

        toMainActivity();
        Toast.makeText(this, "Item was marked sold " , Toast.LENGTH_SHORT).show();

    }

    private void toMainActivity() {
        Intent mainIntent = new Intent( ItemPageActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }
}
