package com.example.tublar;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabSpec tab3 = tabHost.newTabSpec("Third tab");
        TabSpec tab4 = tabHost.newTabSpec("Fourth tab");
    

 // Set the Tab name and Activity
    // that will be opened when particular Tab will be selected
     tab1.setIndicator("MainPage");
     tab1.setContent(new Intent(this,Tab1activity.class));
    
     tab2.setIndicator("Director");
     tab2.setContent(new Intent(this,Tab2activityy.class));

     tab3.setIndicator("Admin");
     tab3.setContent(new Intent(this,Tab3activityy.class));
    
     tab4.setIndicator("trackit");
     tab4.setContent(new Intent(this,Tab4activity.class));
    
     /** Add the tabs  to the TabHost to display. */
     tabHost.addTab(tab1);
     tabHost.addTab(tab2);
     tabHost.addTab(tab3);
     tabHost.addTab(tab4);
    

    }
}





       