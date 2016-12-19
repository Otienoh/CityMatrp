package com.example.tublar;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class Adminhome extends Activity {
	private Button button1,button2,button3;
	private TextView textView1, textView2;
	
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpage);
        addListenerOnButton();
    }
    
    public void addListenerOnButton() {
      	 
    	button1 = (Button) findViewById(R.id.btndir);
    	button2 = (Button) findViewById(R.id.btnrot);
    	button3 = (Button) findViewById(R.id.back);
    	
    	button1.setOnClickListener(new OnClickListener() {
    	     
      	  @Override
      	  public void onClick(View v) {

      		  Intent i = new Intent(getApplicationContext(), Adddirector.class);
		        startActivity(i);
      		  }

      		});
    	button2.setOnClickListener(new OnClickListener() {
    		
    	
   	     
        	  @Override
        	  public void onClick(View v) {

        		  Intent i = new Intent(getApplicationContext(), Addroutee.class);
  		        startActivity(i);
        		  }

        		});
    	button3.setOnClickListener(new View.OnClickListener() {
   	     
    		   @Override
    		    public void onClick(View view) {
    		        // Launching All products Activity
    		        Intent i = new Intent(getApplicationContext(), MainActivity.class);
    		        startActivity(i);

    		    }
    		});	
    }
}




	 
 