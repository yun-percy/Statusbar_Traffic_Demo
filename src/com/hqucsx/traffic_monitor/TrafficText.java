package com.hqucsx.traffic_monitor;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

public class TrafficText extends TextView {
	public static Handler handler; 
    public static int FLAG = 1; 
    TextView textview=TrafficText.this;
	public TrafficText(Context context, AttributeSet attrs) {
		super(context, attrs);
		handler = new Handler(){ 
            @Override 
            public void handleMessage(Message msg) { 
                super.handleMessage(msg); 
                if(msg.what==FLAG){ 
                	if(msg.obj.equals("0")){
                		textview.setVisibility(8);
                	}
                	else{
                		textview.setVisibility(0 );
                		textview.setText((String)msg.obj); 
                	}
                } 
            } 
		};
	}
	

}
