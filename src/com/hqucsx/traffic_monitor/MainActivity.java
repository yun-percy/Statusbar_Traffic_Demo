package com.hqucsx.traffic_monitor;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Intent timeService = null;  
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		timeService = new Intent(MainActivity.this,HandlerService.class); 
		 startService(timeService); 
	}
}
