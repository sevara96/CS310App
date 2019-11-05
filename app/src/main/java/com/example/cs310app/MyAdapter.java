package com.example.cs310app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable {

    Context c;
    ArrayList<Item> items, filterList; //this array lis create a list of  array which parameters define in our item class
    CustomFilter filter;

    public MyAdapter(Context c, ArrayList<Item> items) {
        this.c = c;
        this.items = items;
        this.filterList = items;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext() ).inflate(R.layout.row,null);
        //this line inflates our row

        return new MyHolder(view); //this will return our view to holder class
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {

        myHolder.mTitle.setText(items.get(i).getTitle()); //here i is position
        myHolder.mDes.setText(items.get(i).getDescription());


        //use this code if you want to use ONE activity
        myHolder.setItemClickListener(new ItemClickListener()  {
            @Override
            public void onItemClickListener(View v, int position) {

                String gTitle = items.get(position).getTitle();
                String gDesc = items.get(position).getDescription(); //these objects get our data from previous activity
                /////BitmapDrawable bitmapDrawable = (BitmapDrawable)myHolder.mImaeView.getDrawable(); //this will get our image from drawable
                //^^^^^^ look in the above line if there is an error and it loads android image only
                /////Bitmap bitmap = bitmapDrawable.getBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream(); //image will get stream and bytes

                /////bitmap.compress(Bitmap.CompressFormat.PNG,100, stream); //it will compress our image

                /////byte[] bytes = stream.toByteArray();

                //get our data with intent
                Intent intent = new Intent(c, ItemPageActivity.class);
                intent.putExtra("iTitle", gTitle);
                intent.putExtra("iDesc", gDesc); //get data and put in intent
                /////intent.putExtra("iImage", bytes);
                c.startActivity(intent);

            }
        });

     /*   //use this code if you want to use different activities
        myHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {

                if(items.get(position).getTitle().equals("Chair"){
                    //then you can move another activity from if body
                }
                if(items.get(position).getTitle().equals("Bed"){
                    //then you can move another activity from if body
                }

                //et cetera if statement
            }
        }); */

//        Picasso.get().load("https://i.ebayimg.com/00/s/MTYwMFgxNjAw/z/bdMAAOSwbopZPlIT/$_57.JPG?set_id=8800005007").fit().into(myHolder.mImaeView, new Callback(){
//            @Override
//            public void onSuccess(){
//
//            }
//            @Override
//            public void onError(Exception e){
//                e.printStackTrace();
//
//            }
//        });

        //myHolder.mImaeView.setImageBitmap(items.get(i).getItemPhotoURL());
        //^^here we used image resource because we will use images in our resource folder which is drawable

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {

        if(filter== null){
            filter = new CustomFilter(filterList,this);
        }

        return filter;
    }
}

