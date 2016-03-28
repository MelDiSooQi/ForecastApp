package com.forecastapp.forecastapp.Model.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {
//to check internet connection
	public static boolean isConnectingToInternet(Activity activity)
	{
		ConnectivityManager connectivity = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		  if (connectivity != null) 
		  {
			  NetworkInfo[] info = connectivity.getAllNetworkInfo();
			  if (info != null) 
				  for (int i = 0; i < info.length; i++) 
					  if (info[i].getState() == NetworkInfo.State.CONNECTED)
					  {
						  return true;
					  }
		  }
		  return false;
	}
}
