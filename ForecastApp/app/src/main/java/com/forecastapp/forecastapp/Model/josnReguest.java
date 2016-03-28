package com.forecastapp.forecastapp.Model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.forecastapp.forecastapp.Controller.AppController;
import com.forecastapp.forecastapp.Model.localData.LocalData;
import com.forecastapp.forecastapp.Model.utils.ConnectionDetector;
import com.forecastapp.forecastapp.Model.utils.Const;
import com.forecastapp.forecastapp.View.weatherAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by MelDiSooQi on 3/28/2016.
 */
public abstract class josnReguest {

    protected final Activity activity;
    private String TAG = josnReguest.class.getSimpleName();
    private String tag_json_obj = "jobj_req";

    protected ProgressDialog pDialog;
    private String chooseURL;

    private JSONObject result;

    protected View view;
    protected ListView listView;
    protected weatherAdapter listAdapter;

    protected josnReguest(ProgressDialog pDialog, String chooseURL, Activity activity, View view, ListView listView, weatherAdapter listAdapter)
    {//Receive parameters

        //Declare ProgressDialog
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        //Declare parameters
        this.pDialog    = pDialog;
        this.chooseURL  = chooseURL;
        this.activity   = activity;

        this.view       = view;
        this.listView   = listView;
        this.listAdapter= listAdapter;

        //Check Internet Connection
        if(ConnectionDetector.isConnectingToInternet(activity))
        {//internet Connection enable

            //make Call to backend
            makeJsonObjReq();
        }else
        {//internet Connection disable
            getDataFromLocalStorage();
        }

    }

    private void showProgressDialog() {
        //open ProgressDialog
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        //close ProgressDialog
        if (pDialog.isShowing())
            pDialog.hide();
    }

    private void setResult(JSONObject result)
    {
        this.result = result;
    }

    private JSONObject getResult()
    {
        return result;
    }

    protected abstract void getJSONData(JSONObject result);

    	/**
	 * Making json object request
	 * */
	private void makeJsonObjReq() {
		showProgressDialog();//open ProgressDialog
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, Const.URL_JSON_OBJECT, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                //msgResponse.setText(response.toString());
                setResult(response);
                getJSONData(getResult());
				hideProgressDialog();//close ProgressDialog
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                getDataFromLocalStorage();
                hideProgressDialog();//close ProgressDialog
            }
        })
        {
            /**
             //			 * Passing some request headers
             //			 * */
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				return headers;
			}
        };

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
	}

    private void getDataFromLocalStorage() {
        //get data from local Storage
        String weatherDays = (String) LocalData.getPreference(activity,
                LocalData.PreferenceTypeString, "weatherDays");
        //check if LocalData empty or not
        if(weatherDays != null)
        {//if LocalData not empty
            try {
                // parse local Data if available
                JSONObject jsonObj = new JSONObject(weatherDays);
                getJSONData(jsonObj);

            } catch (JSONException e) {}
        }else
        {//if LocalData empty
            getJSONData(null);
        }
    }
}
