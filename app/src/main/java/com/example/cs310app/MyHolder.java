package com.example.cs310app;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView mImaeView;
    TextView mTitle, mDes, mPrice, mEmail, mPhone;
    ItemClickListener itemClickListener;

    MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImaeView = itemView.findViewById(R.id.imageIv);
        this.mTitle = itemView.findViewById(R.id.titleTv);
        this.mDes = itemView.findViewById(R.id.descriptionTv);
        this.mPrice = itemView.findViewById(R.id.price);
        this.mEmail = itemView.findViewById(R.id.sellerEmail);
        this.mPhone = itemView.findViewById(R.id.sellerPhone);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        this.itemClickListener.onItemClickListener(v, getLayoutPosition());

    }

    public void setItemClickListener(ItemClickListener ic){

        this.itemClickListener = ic;
    }
}

