package com.example.cs310app;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private static final String TAG = "MapsActivity";
    ArrayList<Item> items_ = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        System.out.println("Map page");
        mMap = googleMap;
        final ArrayList<Item> items = new ArrayList<>();
        final Geocoder geo = new Geocoder(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Items");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                items.clear();
                //Get items from database
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Item _item = dataSnapshot1.getValue(Item.class);
                    items.add(_item); // add all data into list.
                    Log.d(TAG, "Read Item: " + _item.getTitle());
                }
                //Add markers onto map for each item
                for(int i = 0; i < items.size(); i++) {

                    String tempAddress = items.get(i).getAddress();
                    Log.d(TAG, tempAddress);
                    List<Address> tempAddresses = null;
                    try {
                        tempAddresses = geo.getFromLocationName(tempAddress, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(tempAddresses == null || tempAddresses.size() == 0) {
                        System.out.println("No address for item: " + items.get(i).getTitle());
                        // Toast.makeText(this, "No address for item: " + items.get(i).getTitle(), Toast.LENGTH_LONG).show();
                        continue;
                    }
                    LatLng tempLatLng = new LatLng(tempAddresses.get(0).getLatitude(), tempAddresses.get(0).getLongitude());
                    //Marker tempMarker = new Marker(tempLatLng);

                    mMap.addMarker(new MarkerOptions().position(tempLatLng).title(
                            "Title: " + items.get(i).getTitle() + ", Price: $" + items.get(i).getPrice()));
                }

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



        LatLng usc = new LatLng(34.019582, -118.289634);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(usc, 14.0f));
    }

}
