package com.forecastapp.forecastapp.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.forecastapp.forecastapp.Model.utils.StatusBar;
import com.forecastapp.forecastapp.R;

public class SplashScreen extends AppCompatActivity
{

	Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		activity = this;
		StatusBar.ChangeStatusBar(activity);

		Thread logoTimer = new Thread()
		{
			private int RESULT_SETTINGS = 1;
			
			public void run()
			{
				try 
				{
					sleep(2500);
					sleep(0);
					Intent i = new Intent(activity, MainActivity.class);
					startActivityForResult(i, RESULT_SETTINGS );
				}
				catch (InterruptedException e) 
				{
					//e.printStackTrace();
				}
				finally
				{
					finish();
				}
			}
		};
		
		logoTimer.start();
		
       
	}
}
