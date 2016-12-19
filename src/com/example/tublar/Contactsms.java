package com.example.tublar;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
 

public class Contactsms extends Activity{
	 Button button;
	    EditText editPhoneNum;
	    EditText editSMS;

	  
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sendmsg);
	 
	        button = (Button) findViewById(R.id.sayhello);
	        editPhoneNum = (EditText) findViewById(R.id.messageNumber);
	        editSMS = (EditText) findViewById(R.id.editSMS);
	 
	        button.setOnClickListener(new OnClickListener() {
	 
	            @Override
	            public void onClick(View v) {
 
	                String phoneNo = editPhoneNum.getText().toString();
	                String sms = editSMS.getText().toString();
	                
	                
	                Intent intentVibrate =new Intent(getApplicationContext(),VibrateService.class);
	          		startService(intentVibrate);
	 
	                try {
	                    SmsManager smsManager = SmsManager.getDefault();
	                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
	                    Toast.makeText(getApplicationContext(), "SMS Sent!",
	                            Toast.LENGTH_LONG).show();
	                } catch (Exception e) {
	                    Toast.makeText(getApplicationContext(),
	                            "SMS faild, please try again later!",
	                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
	                }
	                
	 
	            }
	        });
	    }
	   
	}


