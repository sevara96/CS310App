package com.example.cs310app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


   private  FirebaseAuth mAuth;
   private DatabaseReference userRef;

   private DrawerLayout drawerLayout;
   private NavigationView navigationView;
   private RecyclerView postList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation);
        //View navView = navigationView.inflateHeaderView(R.layout.nav_header);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                UserMenu(menuItem);
                return false;
            }
        });


    }

    private void UserMenu(MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(MainActivity.this, "Profile " , Toast.LENGTH_SHORT).show();
                break;
            case R.id.buy:

                toBuyActivity();

                Toast.makeText(MainActivity.this, "Buy " , Toast.LENGTH_SHORT).show();
                break;
            case R.id.sell:
                Toast.makeText(MainActivity.this, "Sell " , Toast.LENGTH_SHORT).show();
                toSellActivity();
                break;

            case R.id.FindFriends:
                Toast.makeText(MainActivity.this, "Find Friends " , Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
               mAuth.signOut();
               toLogActivity();
               break;
        }

    }


    @Override

    protected  void onStart(){
        super.onStart();
        FirebaseUser currUser = mAuth.getCurrentUser();
        
        if(currUser == null){
            
            toLogActivity();
        } else {
            checkUser();
        }

    }

    private void checkUser() {

        final String currUser = mAuth.getCurrentUser().getUid();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(currUser)){
                    toSetupActivity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void toLogActivity() {
        Intent loginIntent = new Intent( MainActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }


    private void toSetupActivity() {

        Intent setupIntent = new Intent( MainActivity.this, SetupActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();

    }

    private void toBuyActivity() {

        Intent setupIntent = new Intent(MainActivity.this, BuyActivity.class);
    }
    private void toSellActivity() {

        Intent setupIntent = new Intent( MainActivity.this, SellActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();

    }
}
