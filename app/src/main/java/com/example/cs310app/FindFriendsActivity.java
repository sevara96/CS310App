package com.example.cs310app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FindFriendsActivity extends AppCompatActivity {


    private Button SearchButton;
    private EditText searchText;
    private RecyclerView ResultList;
    private DatabaseReference allUsersDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        allUsersDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");
        searchText = findViewById(R.id.inputField);
        SearchButton = findViewById(R.id.search_button);
        ResultList = findViewById(R.id.results);
        ResultList.setHasFixedSize(true);
        ResultList.setLayoutManager(new LinearLayoutManager(this));

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchBox = searchText.getText().toString();
                searchPeople(searchBox);
            }
        });



    }

    private  void searchPeople(String searchBox){

        Toast.makeText(this, "Searching...", Toast.LENGTH_LONG).show();
        Query searchPeopleQuery = allUsersDatabaseRef.orderByChild("fullName").startAt(searchBox).endAt(searchBox + "\uf8ff");



        FirebaseRecyclerAdapter<findFriends, FindFriendsViewHolder> fireBaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<findFriends, FindFriendsViewHolder>(

                findFriends.class, R.layout.display_users,FindFriendsViewHolder.class,searchPeopleQuery)
        {
            @Override
            protected void populateViewHolder(FindFriendsViewHolder findFriendsViewHolder, findFriends model, final int position) {
                findFriendsViewHolder.setFullName(model.getFullName());
                //findFriendsViewHolder.setProfileImage(getApplicationContext(), model.getProfileImage());
                findFriendsViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user_ref_id = getRef(position).getKey();
                        Intent profileIntent = new Intent(FindFriendsActivity.this,PersonProfileActivity.class);
                        profileIntent.putExtra("user_ref_id", user_ref_id);
                        startActivity(profileIntent);
                    }
                });
            }
        };

        ResultList.setAdapter(fireBaseRecyclerAdapter);
    }

    public  static  class FindFriendsViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public  FindFriendsViewHolder(View itemView){
            super(itemView);
            this.mView = itemView;
        }

//        public void setProfileImage(Context ctx, String profileImage) {
//            ImageView myImage = mView.findViewById(R.id.all_users_image);
//           // Picasso.with(ctx).load(ctx).load(profileImage).placeholder(R.drawble)
//
//        }

        public void setFullName(String fullName) {
            TextView myName = mView.findViewById(R.id.all_users_name);
            myName.setText(fullName);
            Log.d("name", myName.toString());


        }

    }
}
