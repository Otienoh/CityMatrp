package com.example.tublar;







import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class Tab3activity extends Activity {
	private Button button;
	private EditText user, pass;
	
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminscreen);
        addListenerOnButton();
    }
    
    public void addListenerOnButton() {
      	 
    	button = (Button) findViewById(R.id.button1);
    	
    	
    	button.setOnClickListener(new OnClickListener() {
    	     
      	  @Override
      	  public void onClick(View v) {

      		  Intent i = new Intent(getApplicationContext(), Adminhome.class);
		        startActivity(i);
      		  }

      		});
    
    }
}




	 
 