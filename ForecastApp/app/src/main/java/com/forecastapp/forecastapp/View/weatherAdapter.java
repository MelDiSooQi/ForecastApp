package com.forecastapp.forecastapp.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.forecastapp.forecastapp.Controller.AppController;
import com.forecastapp.forecastapp.Model.beans.Day;
import com.forecastapp.forecastapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by MelDiSooQi on 3/28/2016.
 */
public class weatherAdapter extends BaseAdapter
{
    private String TAG = weatherAdapter.class.getSimpleName();
    private Activity activity;
    private LayoutInflater inflater;
    private List<Day> days;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    private Typeface CustomFont_Lato_Light;
    private Typeface CustomFont_Lato_Medium;

    public weatherAdapter(Activity activity, List<Day> days) {
        this.activity = activity;
        this.days = days;

        CustomFont_Lato_Light 	= Typeface.createFromAsset(activity.getAssets(), "fonts/Lato-Light.ttf");
        CustomFont_Lato_Medium 	= Typeface.createFromAsset(activity.getAssets(), "fonts/Lato-Medium.ttf");
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int location) {
        return days.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.weather_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView weatherIcon        = (NetworkImageView) convertView.findViewById(R.id.weatherIcon);
        TextView conditionsLabel            = (TextView) convertView.findViewById(R.id.conditionsLabel);
        TextView dayLabel                   = (TextView) convertView.findViewById(R.id.dayLabel);
        TextView extraLabelForFahrenheit    = (TextView) convertView.findViewById(R.id.extraLabelForFahrenheit);
        TextView lowCelsius                 = (TextView) convertView.findViewById(R.id.lowCelsius);
        TextView highCelsius                = (TextView) convertView.findViewById(R.id.highCelsius);
        TextView labelWarning               = (TextView) convertView.findViewById(R.id.label);

        conditionsLabel         .setTypeface(CustomFont_Lato_Medium);
        dayLabel                .setTypeface(CustomFont_Lato_Medium);
        extraLabelForFahrenheit .setTypeface(CustomFont_Lato_Medium);
        lowCelsius              .setTypeface(CustomFont_Lato_Medium);
        highCelsius             .setTypeface(CustomFont_Lato_Medium);
        labelWarning            .setTypeface(CustomFont_Lato_Medium);

        Day day = days.get(position);
        if(day.getId() != -1)
        {
            weatherIcon             .setVisibility(View.VISIBLE);
            conditionsLabel         .setVisibility(View.VISIBLE);
            dayLabel                .setVisibility(View.VISIBLE);
            extraLabelForFahrenheit .setVisibility(View.VISIBLE);
            lowCelsius              .setVisibility(View.VISIBLE);
            highCelsius             .setVisibility(View.VISIBLE);

            weatherIcon             .setImageUrl(day.getConditionsIcon_url(), imageLoader);

            conditionsLabel         .setText(day.getConditions());
            String dateFormated     = dateFormated(day.getDate(),"yyyy/MM/dd h:mm a");//for parsing date with special format
            String dayName          = dateFormated(day.getDate(),"EEEE");//for parsing date with special format
            dayLabel                .setText(dateFormated+" - "+ dayName);
            extraLabelForFahrenheit .setText("Low : " + day.getLowFahrenheit() + " F / " + "High : " + day.getHighFahrenheit() + " F");
            lowCelsius              .setText("Low : " + day.getLowCelsius() + " C");
            highCelsius             .setText("High : " + day.getHighcelsius() + " C");
        }else
        {
            weatherIcon             .setVisibility(View.GONE);
            conditionsLabel         .setVisibility(View.GONE);
            dayLabel                .setVisibility(View.GONE);
            extraLabelForFahrenheit .setVisibility(View.GONE);
            lowCelsius              .setVisibility(View.GONE);
            highCelsius             .setVisibility(View.GONE);

            labelWarning.setText(day.getConditions());
        }
        return convertView;
    }

    private String dateFormated(String date,String outputFormat) {
        try {
            String parseDate = date;
            String[] dateWithoutEET     = parseDate.split("EET on ");
            String dateWithoutEETStr    = "";
            for (int i = 0; i < dateWithoutEET.length; i++) {
                dateWithoutEETStr += dateWithoutEET[i];
            }

            SimpleDateFormat fromUser = new SimpleDateFormat("h:mm a MMMMM dd, yyyy", Locale.US);
            SimpleDateFormat myFormat = new SimpleDateFormat(outputFormat, Locale.US);

            try {
                String reformattedStr = myFormat.format(fromUser.parse(dateWithoutEETStr));
                return reformattedStr;
            } catch (ParseException e) {
                return "";
            }
        }catch (Exception ee)
        {
            return "";
        }

    }

    public void setData(ArrayList<Day> data) {
        this.days = data;
    }
}