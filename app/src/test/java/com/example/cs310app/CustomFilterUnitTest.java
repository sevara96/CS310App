package com.example.cs310app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import android.widget.Filter;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CustomFilterUnitTest {

    ArrayList<Item> filterList;
    MyAdapter adapter;

    CustomFilter cf;


    @Before
    public void setUp(){
        filterList = new ArrayList<>();
        adapter = new MyAdapter(null, null); //is this a problem
        cf = new CustomFilter(filterList, adapter);
    }

    @After
    public void tearDown(){
        cf = null;
    }

    @Test
    public void testConstructor() {
        assertTrue(adapter.equals(cf.adapter));
        assertTrue(filterList.equals(cf.filterList));

    }
}

