package com.example.cs310app;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    ArrayList<Item> filterList;
    MyAdapter adapter;

    public CustomFilter(ArrayList<Item> filterList, MyAdapter adapter) {
        this.filterList = filterList;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();

            ArrayList<Item> filterItems = new ArrayList<>();

            for(int i = 0; i< filterList.size();i++){
                if(filterList.get(i).getTitle().toUpperCase().contains(constraint)){
                    filterItems.add(filterList.get(i));
                }
            }

            results.count = filterItems.size();
            results.values = filterItems;
        }
        else{
            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.items = (ArrayList<Item>) results.values;
        adapter.notifyDataSetChanged();
    }
}
