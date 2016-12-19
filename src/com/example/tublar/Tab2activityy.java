package com.example.tublar;



import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class Tab2activityy extends Activity {
   
	Button button;
    EditText user,pass;
    TextView tv;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directorscreen);
         
        button = (Button) findViewById(R.id.button1); 
        user=(EditText) findViewById(R.id.user1);
  		pass =(EditText) findViewById(R.id.pass1);
        
         
  		button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(Tab2activityy.this, "",
                        "Validating user...", true);
                 new Thread(new Runnable() {
                        public void run() {
                            login();                         
                        }
                      }).start();              
            }
        });
    }
     
    void login(){
        try{           
              
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://192.168.170.15/57983/phptut/dirlog.php"); // make sure the url is correct.
            //add your data
            
            nameValuePairs = new ArrayList<NameValuePair>(2);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
            nameValuePairs.add(new BasicNameValuePair("Email",user.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("Password",pass.getText().toString().trim()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            response=httpclient.execute(httppost);
           
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                public void run() {
                    //tv.setText("Response from PHP : " + response);
                    dialog.dismiss();
                }
            });
             
            if(response.equalsIgnoreCase("User Found")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(Tab2activityy.this,"Login Success", Toast.LENGTH_SHORT).show();
                    }
                });
                 
                startActivity(new Intent(Tab2activityy.this, Dirhome.class));
            }else{
                showAlert();               
            }
             
        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }
    public void showAlert(){
        Tab2activityy.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Tab2activityy.this);
                builder.setTitle("Login Error.");
                builder.setMessage("Inncorrect Credentials.") 
                       .setCancelable(false)
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                           }
                       });                    
                AlertDialog alert = builder.create();
                alert.show();              
            }
        });
    }
    @Override
   	protected void onResume() {
   		// TODO Auto-generated method stub
   		super.onResume();
   		super.onResume();
        pass.setText("");
        user.setText("");
   	}
}