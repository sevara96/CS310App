package com.example.cs310app;

import java.util.Comparator;
import java.util.List;

public class Item {


    //member variables
    private String description;
//    private String address;
    private String itemPhotoURL;
    private String title;
//    private List<String> tags;
    private double price;


    //constructor
    public Item(String title, String itemPhotoURL, String description){
        this.title=title;
        this.itemPhotoURL=itemPhotoURL;
        this.description=description;
    }

    public Item(String title, String itemPhotoURL, String description, double price){
        this.title=title;
        this.itemPhotoURL=itemPhotoURL;
        this.description=description;
        this.price = price;
    }

    public Item(){}

//    public void addTag(String tag){
//        tags.add(tag);
//    }

    public String getDescription() {
        return description;
    }

//    public String getAddress() {
//        return address;
//    }

    public String getItemPhotoURL() {
        return itemPhotoURL;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemPhotoURL(String itemPhotoURL) {
        this.itemPhotoURL = itemPhotoURL;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) { this.price = price;}

    public static final Comparator<Item> By_PRICE_ASCENDING = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            //return o1.getTitle().compareTo(o2.getTitle());

            return Double.compare(o1.getPrice(),o2.getPrice());
        }
    };

    public static final Comparator<Item> By_PRICE_DESCENDING = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            //return o2.getTitle().compareTo(o1.getTitle());

            return Double.compare(o2.getPrice(),o1.getPrice());
        }
    };



}

