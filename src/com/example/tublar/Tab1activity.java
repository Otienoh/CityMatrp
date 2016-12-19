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
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



public class Tab1activity extends Activity {
	
	 private Spinner spinner1;
	 private Button btnSubmit; 
	 EditText routefare,routeno,routedirector,hotline;
	 String result;
		JSONObject jobj;
		String [] location;
		SQLiteDatabase db;
		Sqlite helper=new Sqlite(this);
	ArrayList<String> items=new ArrayList<String>();
		
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        db=helper.getReadableDatabase();
       
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        routefare=(EditText)findViewById(R.id.routefare);
        routeno=(EditText)findViewById(R.id.routeno);
        routedirector=(EditText)findViewById(R.id.routedirector);
        hotline=(EditText)findViewById(R.id.Hotlines);
        
        populateSpinner();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        spinner1.setAdapter(adapter);
        
       
        addListenerOnButton();
    	addListenerOnSpinnerItemSelection();
    }
	 
	 

	public void addListenerOnSpinnerItemSelection() {
	    	//spinner1 = (Spinner) findViewById(R.id.spinner1);
	    	spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	    }
	 
	 // get the selected dropdown list value
	    public void addListenerOnButton() {
	    	 
	    	spinner1 = (Spinner) findViewById(R.id.spinner1);
	    	
	    	btnSubmit = (Button) findViewById(R.id.btnSubmit);
	    	
	     
	    	btnSubmit.setOnClickListener(new OnClickListener() {
	     
	    	  @Override
	    	  public void onClick(View v) {
	    		  
	    		    Toast.makeText(Tab1activity.this,
	    			"Selected destination : " + 
	    	              "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()),
	    				Toast.LENGTH_SHORT).show();
	    		    //readData();
	    		  //  String.valueOf(spinner1.getSelectedItem())
	    		    readDB(String.valueOf(spinner1.getSelectedItem()));
	    		  }

	    		});

	    
	    	}
	    
	    private void populateSpinner() {
	    	String sql="SELECT * FROM route";
			Cursor c=db.rawQuery(sql, null);
			
			c.moveToFirst();
			while(c.moveToNext()){			
				
				String location=c.getString(c.getColumnIndex("Route_location")); 
				items.add(location);
				
				//Toast.makeText(getApplicationContext(), "listing fares"+fare, Toast.LENGTH_LONG).show();
			}
		}
	    
		protected void readDB(String where) {
			
			
			String sql="SELECT * FROM route WHERE Route_location='"+where+"'";
			Cursor c=db.rawQuery(sql, null);
			
			c.moveToFirst();
			while(c.moveToNext()){
				
				
				String fare=c.getString(c.getColumnIndex("Route_fare"));
				String no=c.getString(c.getColumnIndex("Route_no")); 
				String director=c.getString(c.getColumnIndex("Route_director"));
				String S_hotline=c.getString(c.getColumnIndex("Hotline"));
				//Toast.makeText(getApplicationContext(), "listing fares"+fare, Toast.LENGTH_LONG).show();
				
				routefare.setText(fare);
				routeno.setText(no);
				routedirector.setText(director);
				hotline.setText(S_hotline);
			}
			
			
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
						HttpPost httppost=new HttpPost("http://192.168.170.15/57983/phptut/test.php");

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
							//List<String> list = new ArrayList<String>();
							
							//location[i]=jobj.getString("Route_location");
					String 	fare=jobj.getString("Route_fare"); 
					String location=jobj.getString("Route_location");
					String director=jobj.getString("Route_director");
					String hotline=jobj.getString("Hotline");
						  
					Toast.makeText(getApplicationContext(), location+" "+fare, Toast.LENGTH_LONG).show();
					
					//routefare.setText(fare);
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







