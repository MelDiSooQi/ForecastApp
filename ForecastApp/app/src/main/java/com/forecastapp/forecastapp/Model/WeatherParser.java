package com.forecastapp.forecastapp.Model;

import com.forecastapp.forecastapp.Model.beans.Day;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by MelDiSooQi on 3/28/2016.
 */
public class WeatherParser
{
    private String TAG = WeatherParser.class.getSimpleName();

    //Parsing Data from JSON
    public WeatherParser(JSONObject result) {
        try
        {
            //Parsing Data from JSON
            JSONObject forecastObj = result.getJSONObject("forecast");
            JSONObject simpleforecastObj    = forecastObj.getJSONObject("simpleforecast");

            parseSimpleforecast(simpleforecastObj);
        }
        catch (JSONException e)
        {
        }
    }

    private ArrayList<Day> daysArrayList = new ArrayList<Day>();

    private ArrayList<Day> parseSimpleforecast(JSONObject simpleforecastObj) throws JSONException
    {

        JSONArray forecastdayObj    = simpleforecastObj.getJSONArray("forecastday");

        for(int i=0; i<forecastdayObj.length(); i++)
        {
            JSONObject weatherOneDay = forecastdayObj.getJSONObject(i);

            int id                  = i;
            String date             = weatherOneDay.getJSONObject("date").getString("pretty");

            int highFahrenheit      = weatherOneDay.getJSONObject("high").getInt("fahrenheit");
            int highcelsius         = weatherOneDay.getJSONObject("high").getInt("celsius");

            int lowFahrenheit       = weatherOneDay.getJSONObject("low").getInt("fahrenheit");
            int lowCelsius          = weatherOneDay.getJSONObject("low").getInt("celsius");

            String conditions       = weatherOneDay.getString("conditions");
            String conditionsIcon_url  = weatherOneDay.getString("icon_url");

            daysArrayList.add(new Day(id, date, highFahrenheit, highcelsius, lowFahrenheit, lowCelsius,
                                        conditions, conditionsIcon_url));
        }
        return daysArrayList;
    }

    public ArrayList<Day> getDaysArrayList() {
        return daysArrayList;
    }
}
