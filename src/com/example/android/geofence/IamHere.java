/**
 * 
 */
package com.example.android.geofence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

/**
 * @author jaichaudhary
 *
 */
public class IamHere {

	private static int recordID;

	/**
	 * Sends POST request to iamhere virtual host on smalldata server
	 * @param floor String
	 * 
	 */
	public void setEntry(String floor){
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://iamhere.smalldata.io/occupancy");
	
		try {
		    // Encode Form data
		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		    nameValuePairs.add(new BasicNameValuePair("name", "Jai Chaudhary"));
		    nameValuePairs.add(new BasicNameValuePair("floor", floor));
		    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		
		    // Execute HTTP Post Request
		    HttpResponse response = httpclient.execute(httppost);
		    JSONObject jsonResponse = new JSONObject(EntityUtils.toString(response.getEntity()));
		    IamHere.recordID = Integer.parseInt(jsonResponse.getString("id"));
		    Log.d("Http Post Response:", jsonResponse.getString("id"));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * Sends PUT request to delete iamhere record on smalldata server
	 * @param floor number
	 * 
	 */
	public void setExit(){
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPut httpput = new HttpPut("http://iamhere.smalldata.io/occupancy/" + IamHere.recordID + "/depart");
	
		try {			
			// Execute HTTP Put Request
			HttpResponse response = httpclient.execute(httpput);
			Log.d("Http Put Response:", EntityUtils.toString(response.getEntity()));
			Log.d("Http Put Response:", "http://iamhere.smalldata.io/occupancy/" + IamHere.recordID + "/depart");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends PUT request to delete iamhere record on smalldata server
	 * @param floor number
	 * 
	 */
	public void updatePresence(){
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPut httpput = new HttpPut("http://iamhere.smalldata.io/occupancy/" + IamHere.recordID + "/update");
	
		try {			
			// Execute HTTP Put Request
			HttpResponse response = httpclient.execute(httpput);
			Log.d("Http Put Response:", EntityUtils.toString(response.getEntity()));
			Log.d("Http Put Response:", "http://iamhere.smalldata.io/occupancy/" + IamHere.recordID + "/update");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
