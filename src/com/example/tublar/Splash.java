package com.example.tublar;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Splash extends Activity {

	MediaPlayer song;
	String result;
	SQLiteDatabase db;
	Sqlite helper=new Sqlite(this);
	public boolean flag=false;
   // Splash screen timer
   private static int SPLASH_TIME_OUT = 3000;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.splash);
       db=helper.getWritableDatabase();
       
       flag=getSharedPreferences("PREF",MODE_PRIVATE).getBoolean("flag", false);
       
       readData();
       song = MediaPlayer.create(Splash.this , R.raw.splashsng);
       	song.start();
    		   Thread timer = new Thread (){
           @Override
           public void run() {   
        	 
               try{
            	   sleep(10000);
               }catch(Exception e){
            	   e.printStackTrace();
               }finally{
            	   Intent i = new Intent("com.example.tublar.SPLASH");
                   startActivity(i); 
                   
                  
               }
              
               
           }
       };
       timer.start();
   }

   protected void readData() {
		new AsyncTask<String,Void,String>(){

			protected void onPreExecute() {
				
				Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
				
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
				
				//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
				try {
					JSONArray json_array=new JSONArray(result);
					
					for(int i=0;i<json_array.length();i++){
						JSONObject jobj=json_array.getJSONObject(i);
						
				String 	no=jobj.getString("Route_no");
				String 	fare=jobj.getString("Route_fare"); 
				String location=jobj.getString("Route_location");
				String director=jobj.getString("Route_director");
				String hotline=jobj.getString("Hotline");
				
				ContentValues values=new ContentValues();
				values.put("Route_fare", fare);
				values.put("Route_no", no);
				values.put("Route_location", location);
				values.put("Route_director", director);
				values.put("Hotline", hotline);
				
			
			if(flag==false){
				db.insert("route",null, values);
				
				getSharedPreferences("PREF", MODE_PRIVATE).edit().putBoolean("flag", true);
			}else{
				
				db.update("route", values, null, null);
			}	
				
					  
				//Toast.makeText(getApplicationContext(), json_array.length()+" "+location+" "+fare+" "+director+" "+hotline, Toast.LENGTH_LONG).show();
				
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

   
@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	song.release();
	finish();
}

}