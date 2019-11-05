package com.example.cs310app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ItemPageActivity extends AppCompatActivity {

    TextView mTitleTv, mDescTv;
    ImageView mImageIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        ActionBar actionBar = getSupportActionBar();

        mTitleTv = findViewById(R.id.title);
        mDescTv= findViewById(R.id.description);
        mImageIv =findViewById(R.id.imageView);

        //get data from intent where we put our data
        Intent intent = getIntent();

        String mTitle = intent.getStringExtra("iTitle");
        String mDescription = intent.getStringExtra("iDesc");

        /////byte[] mBytes = getIntent().getByteArrayExtra("iImage");
        //now you need to decode the image because from preious activity we get our image in bytes

        /////Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes, 0, mBytes.length);

        actionBar.setTitle(mTitle); //which title we get from previous activity that will get in our action bar

        //now get our data in our view, which we get in our previous activitiy

        mTitleTv.setText(mTitle);
        mDescTv.setText(mDescription);
        /////mImageIv.setImageBitmap(bitmap);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);


        return true;
    }
}
