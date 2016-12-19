package com.example.tublar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Addroutee extends Activity {
	private Button button1,button2;
	private TextView textView1, textView2;
	private EditText Routeno, Fare, director, hotline, location;
	ProgressDialog pg;
	String rt, rf, rd, rh,rl;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_route);
        addListenerOnButton();
        
        
    }
    
    public void addListenerOnButton() {
      	 
    	button1 = (Button) findViewById(R.id.add);
    	//button2 = (Button) findViewById(R.id.button2);
    	
    	
    	button1.setOnClickListener(new OnClickListener() {
    	     
      	  @Override
      	  public void onClick(View v) {
      		//String result = ((EditText) findViewById(R.id.routediret)).getText().toString(), ().getText().toString(), ().getText().toString());
      		///Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
      		Routeno=(EditText) findViewById(R.id.routenoet);
      		Fare =(EditText) findViewById(R.id.routefareet);
      		director = (EditText) findViewById(R.id.routediret);
      		hotline =(EditText) findViewById(R.id.hotet);
      		location = (EditText) findViewById(R.id.locationet);
      		
      		 rt = Routeno.getText().toString();
      		rf = Fare.getText().toString();
      		rd = director.getText().toString();
      		rh = hotline.getText().toString();
      		rl = location.getText().toString();
      		
      		Intent intentVibrate =new Intent(getApplicationContext(),VibrateService.class);
      		startService(intentVibrate);
      		
      		  sendData();
      		  }

      		});

    	
}

private void sendData() {
		
		new AsyncTask<String,Void,String>(){

			protected void onPreExecute() {
				 pg=ProgressDialog.show(Addroutee.this,null ,"Creating Route...",true,false);
				//Toast.makeText(getApplicationContext(), "loading", Toast.LENGTH_LONG).show();
			};
		@Override
			protected String doInBackground(String... arg0) {
				
				//create Http client and post header
				HttpClient httpclient=new DefaultHttpClient();
				HttpPost httppost=new HttpPost("http://192.168.170.15/57983/phptut/addroute.php");
				
				//add my data
				 try {
			            // Add your data
			            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			            nameValuePairs.add(new BasicNameValuePair("Route_no", rt));
			            nameValuePairs.add(new BasicNameValuePair("Route_fare", rf));
			            nameValuePairs.add(new BasicNameValuePair("Route_director", rd));
			            nameValuePairs.add(new BasicNameValuePair("Hotline", rh));
			            nameValuePairs.add(new BasicNameValuePair("Route_location", rl));
			           
			            
			            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			            //Execute HTTP Post Request
			           HttpResponse response = httpclient.execute(httppost);
			      
			            
			            

			        } catch (ClientProtocolException e) {
			            // TODO Auto-generated catch block
			        } catch (IOException e) {
			            // TODO Auto-generated catch block
			        }
				
				
			return null;
			}
			
			protected void onPostExecute(String result) {
				
				
				Toast.makeText(getApplicationContext(), "Route created", Toast.LENGTH_LONG).show();
				pg.dismiss();
			};
		
		
		}.execute();
    
    
}


}