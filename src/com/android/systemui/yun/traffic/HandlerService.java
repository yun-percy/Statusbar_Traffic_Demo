package com.android.systemui.yun.traffic; 
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;
 
public class HandlerService extends Service{ 

    private long total_data = TrafficStats.getTotalRxBytes();
    private Handler mHandler;
    //几秒刷新一次
    private final int count = 3;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            //定时器
            mHandler.postDelayed(mRunnable, count * 1000);
            Message msg = mHandler.obtainMessage();
            msg.what = 1;
            msg.arg1 = getNetSpeed();
            mHandler.sendMessage(msg);
            
        }
    };
    private int getNetSpeed() {  
        long traffic_data = TrafficStats.getTotalRxBytes() - total_data;
        total_data = TrafficStats.getTotalRxBytes();
        return (int)traffic_data /count ;
    }
    @Override 
    public IBinder onBind(Intent intent) { 
        return null; 
    } 
 
    @Override 
    public void onCreate() { 
        super.onCreate();
        mHandler = new Handler(){
            @Override
            public void handleMessage(final Message msg) {
                super.handleMessage(msg);
                Message msg2 = new Message(); 
                msg2.what = TrafficText.FLAG; 
                if (msg.what == 1) {
                    if(msg.arg1  > 1024 ){
                         msg2.obj=msg.arg1 / 1024 + "kb/s"; 
                    }
                    else{
                   
                        if(msg.arg1>0){
                        	msg2.obj=msg.arg1 + "b/s"; 
                        }
                        else msg2.obj="0"; 
                    }
                    System.out.println(msg2.obj);
                    TrafficText.handler.sendMessage(msg2); 
                }
            }
        };
       
         
    } 
 
  
    @Override
    public void onStart(Intent intent, int startId) {
        mHandler.postDelayed(mRunnable, 0);
    };
 
 
    @Override 
    public void onDestroy() { 
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy(); 
         
    } 

     
     
} 