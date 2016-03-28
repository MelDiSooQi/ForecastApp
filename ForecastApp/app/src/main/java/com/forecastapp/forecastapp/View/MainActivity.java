package com.forecastapp.forecastapp.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.forecastapp.forecastapp.Controller.Caller;
import com.forecastapp.forecastapp.Model.beans.Day;
import com.forecastapp.forecastapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private ListView listView;
    private weatherAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Day> initList = new ArrayList<Day>();// make empty Array for initialization

        listView = (ListView) findViewById(R.id.list);  //declare List View
        listAdapter = new weatherAdapter(this,initList);//Create Adapter for List View
        listView.setAdapter(listAdapter);

        //Call api to get Data from backend
        new Caller(pDialog, MainActivity.this, MainActivity.this.listView, listView, listAdapter);

        //Floating button for refresh
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Call api to get Data from backend
                new Caller(pDialog, MainActivity.this, view, listView, listAdapter);
            }
        });
    }
}
