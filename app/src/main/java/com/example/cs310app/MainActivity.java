package com.example.cs310app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


   private  FirebaseAuth mAuth;
   private DatabaseReference userRef;

   private DrawerLayout drawerLayout;
   private NavigationView navigationView;
   private RecyclerView postList;

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    SharedPreferences preferences;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//////////////
        ImageView im = findViewById(R.id.pic);
        mRecyclerView = findViewById(R.id.myrecyclerView);
        preferences = this.getSharedPreferences("My_Pref", MODE_PRIVATE);
        getMyList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/////////////
        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        //drawerLayout = findViewById(R.id.drawerLayout);
        //navigationView = findViewById(R.id.navigation);
        //View navView = navigationView.inflateHeaderView(R.layout.nav_header);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//                UserMenu(menuItem);
//                return false;
//            }
//        });


    }
    public void tosellpage(View view)
    {
        Intent intent = new Intent(MainActivity.this, SellActivity.class);
        startActivity(intent);
    }
    public void toprofilepage(View view)
    {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                myAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                myAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }

    private void getMyList(){

        final ArrayList<Item> items = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Items");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                items.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Item _item = dataSnapshot1.getValue(Item.class);
                    items.add(_item); // add all data into list.
//
                    Log.d(TAG, "Read Item: " + _item.getTitle());
                }

                String mSortSetting = preferences.getString("Sort", "ascending");

                if(mSortSetting.equals("ascending")){
                    Collections.sort(items, Item.By_PRICE_ASCENDING);
                }
                else if(mSortSetting.equals("descending")){
                    Collections.sort(items, Item.By_PRICE_DESCENDING);
                }

                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                myAdapter = new MyAdapter(MainActivity.this , items );
                mRecyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        Item _item = new Item();
        _item.setTitle("Chair");
        _item.setPrice(25.00);
        _item.setDescription("Good brown Ikea chair");
        _item.setItemPhotoURL("https://www.pier1.com/dis/dw/image/v2/AAID_PRD/on/demandware.static/-/Sites-pier1_master/default/dw03df652e/images/2691730/2691730_1.jpg?sw=1600&sh=1600");
        items.add(_item);

        _item = new Item();
        _item.setTitle("Bed");
        _item.setPrice(50.00);
        _item.setDescription("Queen bed with sheets");
        _item.setItemPhotoURL("https://i.ebayimg.com/00/s/MTYwMFgxNjAw/z/bdMAAOSwbopZPlIT/$_57.JPG?set_id=8800005007");
        items.add(_item);

        _item = new Item();
        _item.setTitle("Paring Knife");
        _item.setPrice(5.00);
        _item.setDescription("Great for fruits and vegetables");
        _item.setItemPhotoURL("https://cdn.shopify.com/s/files/1/1308/4697/products/39910_Paring_Knife.png?v=1527283568");
        items.add(_item);

//        _item = myRef.child("randomsnb").getValue(Item.class);
//        items.add(_item);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.sorting){
            sortDialog();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void sortDialog() {

        String[] options = {"Ascending Price", "Descending Price"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sort by");
        builder.setIcon(R.drawable.ic_action_sort); //adding icon

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "ascending");
                    editor.apply();
                    getMyList();
                }

                if(which == 1){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "descending");
                    editor.apply();
                    getMyList();
                }
            }
        });

        builder.create().show();

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
