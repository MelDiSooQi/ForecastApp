package com.forecastapp.forecastapp.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ListView;

import com.forecastapp.forecastapp.Model.WeatherParser;
import com.forecastapp.forecastapp.Model.beans.Day;
import com.forecastapp.forecastapp.Model.josnReguest;
import com.forecastapp.forecastapp.Model.localData.LocalData;
import com.forecastapp.forecastapp.Model.utils.ConnectionDetector;
import com.forecastapp.forecastapp.View.weatherAdapter;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by MelDiSooQi on 3/28/2016.
 */
public class Caller extends josnReguest {

    private String TAG = Caller.class.getSimpleName();

    public Caller(ProgressDialog pDialog, Activity activity, View view, ListView listView, weatherAdapter listAdapter)
    {
        //send parameters for (josnReguest) class
        super(pDialog, null, activity, view, listView, listAdapter);
    }

    @Override
    protected void getJSONData(JSONObject result)
    {
        if(result != null) {
            //set Weather Days in Local Data Storage
                LocalData.setPreference(activity.getApplicationContext(),
                        LocalData.PreferenceTypeString,
                        "weatherDays",
                        result.toString());

            WeatherParser weatherParser = new WeatherParser(result);

            ArrayList<Day> threeOnly = new ArrayList<Day>();
            // to display only three
            for (int i = 0; i < 3; i++) {
                threeOnly.add(weatherParser.getDaysArrayList().get(i));
            }

            listAdapter.setData(threeOnly);
            listAdapter.notifyDataSetChanged();

            if(ConnectionDetector.isConnectingToInternet(activity)) {
                Snackbar.make(view, "Refresh Done...", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }else
            {
                Snackbar.make(view, "Please, Check Internet Connection...", Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show();
            }
        }else
        {
            ArrayList<Day> initList = new ArrayList<Day>();
            initList.add(new Day(-1,"",0,0,0,0,"Please, Check Internet Connection...",""));
            listAdapter.setData(initList);
            listAdapter.notifyDataSetChanged();
        }
    }
}
