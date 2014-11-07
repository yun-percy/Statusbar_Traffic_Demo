package com.android.systemui.yun.traffic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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
