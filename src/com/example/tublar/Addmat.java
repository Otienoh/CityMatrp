
package com.example.tublar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Addmat extends Activity {
	private Button button1;
	ProgressDialog pg;
	private EditText Routeno, Regno, Driver, Phone;
	String mrn,mrg,md,mp;
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmat);
        addListenerOnButton();
    }
    
    public void addListenerOnButton() {
    	 
    	button1 = (Button) findViewById(R.id.add);
    	//
    	
    	
    	button1.setOnClickListener(new OnClickListener() {
    	     
      	  @Override
      	  public void onClick(View v) {
      		//String result = ((EditText) findViewById(R.id.routediret)).getText().toString(), ().getText().toString(), ().getText().toString());
      		///Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
      		Regno=(EditText) findViewById(R.id.regno);
      		Routeno=(EditText) findViewById(R.id.routeno);
      		Driver =(EditText) findViewById(R.id.name);
      		Phone = (EditText) findViewById(R.id.phone);
      		
      		mrg = Regno.getText().toString();
      		mrn = Routeno.getText().toString();
      		md = Driver.getText().toString();
      		mp = Phone.getText().toString();
      		
      		Intent intentVibrate =new Intent(getApplicationContext(),VibrateService.class);
      		startService(intentVibrate);
      		      		
      		  sendData();
      		  }

      		});

    	
}

private void sendData() {
		
		new AsyncTask<String,Void,String>(){

			protected void onPreExecute() {
				 pg=ProgressDialog.show(Addmat.this,null ,"Adding Matatu...",true,false);
				
			};
		@Override
			protected String doInBackground(String... arg0) {
				
				//create Http client and post header
				HttpClient httpclient=new DefaultHttpClient();
				HttpPost httppost=new HttpPost("http://192.168.170.15/57983/phptut/Addmat.php");
				
				//add my data
				 try {
			            // Add your data
			            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			            nameValuePairs.add(new BasicNameValuePair("Matatu_regno", mrg));
			            nameValuePairs.add(new BasicNameValuePair("Route_no", mrn));
			            nameValuePairs.add(new BasicNameValuePair("Matatu_driver", md));
			            nameValuePairs.add(new BasicNameValuePair("Phone", mp));
			          
			            
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
				
				
				Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
				pg.dismiss();
			};
		
		
		}.execute();
    
    
}

    

}
    
