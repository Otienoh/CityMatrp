
package com.example.tublar;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class Dirhome extends Activity {
	private Button button1,button2;
	ListView nameList;
	String result;
	
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directorpage);
        addListenerOnButton();
    }
    
    public void addListenerOnButton() {
      	 
    	button1 = (Button) findViewById(R.id.btnmat);
    	button2 = (Button) findViewById(R.id.btnSubmit);
    	
    	
    	button1.setOnClickListener(new OnClickListener() {
    	     
      	  @Override
      	  public void onClick(View v) {

      		  Intent i = new Intent(getApplicationContext(), Addmat.class);
		        startActivity(i);
      		  }  

      		});
    	button2.setOnClickListener(new OnClickListener() {
    		
    	
   	     
        	  @Override
        	  public void onClick(View v) {

        		  //Intent i = new Intent(getApplicationContext(.class);
  		       // startActivity(i);
        		  //sendData();
        		  readData();
        		  }
        	  

        		});
    }
    



		protected void readData() {
			new AsyncTask<String,Void,String>(){

				protected void onPreExecute() {
					
					Toast.makeText(getApplicationContext(), "getting details...", Toast.LENGTH_LONG).show();
					
				};
				@Override
				protected String doInBackground(String... arg0) {
					
					//connect to server 
					try {
						
						//create Http client and post header
						HttpClient httpclient=new DefaultHttpClient();
						HttpPost httppost=new HttpPost("http://10.0.2.2/phptut/test.php");

						HttpParams par = httppost.getParams();

						int connection_Timeout = 6500;// timeout until connection is
														// established
						int socket_timeout = 6500;// timeout socket
						HttpConnectionParams.setConnectionTimeout(par,
								connection_Timeout);
						HttpConnectionParams.setConnectionTimeout(par, socket_timeout);

						
						
						
			            
						HttpResponse Response = httpclient.execute(httppost);
						Response.getEntity();

						BufferedReader buf = new BufferedReader(new InputStreamReader(Response
								.getEntity().getContent()));

						StringBuffer sb = new StringBuffer("");
						String line = "";
						String NL = System.getProperty("line.separator");
						while ((line = buf.readLine()) != null) {

							sb.append(line + NL);

						}

						buf.close();
						result = sb.toString();

						return result;

					} catch (UnsupportedEncodingException e) {
						result = null;
						e.printStackTrace();
					} catch (ClientProtocolException e) {
						result = null;
						e.printStackTrace();
					} catch (SocketTimeoutException e) {
						result = null;
						e.printStackTrace();
					} catch (ConnectTimeoutException e) {
						result = null;
						e.printStackTrace();
					} catch (IOException e) {
						result = null;
						e.printStackTrace();
					}
					return result;
				}
				
			protected void onPostExecute(String result) {
				
				if(result!=null){
					
					Toast.makeText(getApplicationContext(), "success  ", Toast.LENGTH_LONG).show();
					try {
						JSONArray json_array=new JSONArray(result);
						
						for(int i=0;i<json_array.length();i++){
							JSONObject jobj=json_array.getJSONObject(i);
							
					String 	fare=jobj.getString("Route_fare"); 
					String location=jobj.getString("Route_location");
						  
					Toast.makeText(getApplicationContext(), location+" "+fare, Toast.LENGTH_LONG).show();
						}
						
						
						
						
					} catch (JSONException e) {
						
						e.printStackTrace();
					} 
				}
				else{
					
					Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
				}
				
			};
			}.execute();
			
		}
		
 
}