package com.example.tublar;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Tab4activity extends Activity {
 
private Button button1,button2;
EditText Regno;




public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.trackmat);
    addListenerOnButton();
    
}

public void addListenerOnButton() {
  	 
	button1 = (Button) findViewById(R.id.btnSubmit);
	button2 = (Button) findViewById(R.id.contact);
	
	
	
	button1.setOnClickListener(new OnClickListener() {
	     
  	  @Override
  	  public void onClick(View v) {
  		  
  		Regno= (EditText) findViewById(R.id.regno);
  		getMatdetails();
  		
  		  }

  		});
	
	button2.setOnClickListener(new OnClickListener() {
	     
	  
	  	 @Override
     	  public void onClick(View v) {

     		  Intent i = new Intent(getApplicationContext(), Contactsms.class);
		        startActivity(i);
     		  } 

	  		});

	
}

protected void getMatdetails() {
	// TODO Auto-generated method stub
	//AsyncTask<Params, Progress, Result>
	
	
}}


