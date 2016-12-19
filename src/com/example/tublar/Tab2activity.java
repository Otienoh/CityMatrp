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

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tab2activity extends Activity {
	private Button button;
	private EditText user, pass;
	ProgressDialog pg;
	String dlu, dlp;
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directorscreen);
        addListenerOnButton();
    }
    
    public void addListenerOnButton() {
      	 
    	button = (Button) findViewById(R.id.button1);
    	
    	
    	button.setOnClickListener(new OnClickListener() {
    	     
      	  @Override
      	  public void onClick(View v) {

      		 // Intent i = new Intent(getApplicationContext(), Dirhome.class);
		        //startActivity(i);
      		user=(EditText) findViewById(R.id.user1);
      		pass =(EditText) findViewById(R.id.pass1);
      		
      		
      		 dlu = user.getText().toString();
      		dlp = pass.getText().toString();
      		
      		Login();
      		  }

      		});
    
    }

	protected void Login() {
		// TODO Auto-generated method stub
		
		new AsyncTask<String,Void,String>(){

			protected void onPreExecute() {
				 pg=ProgressDialog.show(Tab2activity.this,null ,"Authenticating...",true,false);
				
			};
		@Override
			protected String doInBackground(String... arg0) {
				
				//create Http client and post header
				HttpClient httpclient=new DefaultHttpClient();
				HttpPost httppost=new HttpPost("http://192.168.162.131/phptut/longin.php");
				
				//add my data
				 try {
			            // Add your data
			            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			            nameValuePairs.add(new BasicNameValuePair("Email", dlu));
			            nameValuePairs.add(new BasicNameValuePair("Password", dlp));
			           
			        
			            
			            
			            
			            
			            
			          
			            
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






	
	
