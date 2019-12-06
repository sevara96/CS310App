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

import java.io.ByteArrayOutputStream;
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

                String gPrice = items.get(position).getStringPrice();
                String gLocation = items.get(position).getAddress();
                String gFullName =items.get(position).getFullName();
                String gEmail = items.get(position).getEmail();
                String gPhone = items.get(position).getPhone();

                ByteArrayOutputStream stream = new ByteArrayOutputStream(); //image will get stream and bytes

                //get our data with intent
                Intent intent = new Intent(c, ItemPageActivity.class);
                intent.putExtra("iTitle", gTitle);
                intent.putExtra("iDesc", gDesc); //get data and put in intent
                intent.putExtra("iPrice", gPrice);
                intent.putExtra("iLocation", gLocation);
                intent.putExtra("iFullName", gFullName);
                intent.putExtra("iEmail", gEmail);
                intent.putExtra("iPhone", gPhone);
                c.startActivity(intent);

            }
        });

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

