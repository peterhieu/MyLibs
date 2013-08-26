package com.example.datadiagramsocket;

import java.util.ArrayList;
import java.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayTextRecievedActivity extends Activity {

	public TextView 		m_SystemTimeTextView;
	public ListView 		m_SystemTimeListView;
	public Button 			m_CancelButton;
	
	static ArrayList<String> 		dataItem;
	static ArrayAdapter<String> 	adapter;
	static Handler 			handerThr;
	static String					ip;
	static String					port;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_screen);
		
		Intent i 	= getIntent();
		Bundle bdl 	= i.getBundleExtra("intentFromMain");
		ip 			= bdl.getString("ip", "10.0.2.15");
		port 		= bdl.getString("port", "4000");
		
		handerThr = new Handler();
		
		m_SystemTimeListView = (ListView)findViewById(R.id.lvListSystemTime);  
		dataItem = new ArrayList<String>();		
		adapter = new ArrayAdapter<String>(this,R.layout.single_list_item, dataItem);
		
		m_SystemTimeListView.setAdapter(adapter);		
		
		Log.d("in onCreate DisplayTexRecieved Activity", " let trace");
		Log.d("ip", ip);
		Log.d("port", port);		
		
		DataDiagramSocket dtDgramSk = DataDiagramSocket.getInstance();
		
		Thread th = new Thread(dtDgramSk);
	
		th.start();    		
	}

	/**
	 * class UpdateThread
	 * */
	public static class UpdateThread implements Runnable{

		String msg;
		
		UpdateThread(byte[] Message){
			msg=new String(Message, 0, Message.length);
		}
		
		@Override
		public void run() {
			Calendar ca = Calendar.getInstance();
			if(msg!=null){
				dataItem.add(msg + ca.get(Calendar.HOUR)+"/"+ca.get(Calendar.MINUTE)+"/"+ca.get(Calendar.SECOND));
		        adapter.notifyDataSetChanged();
			}	
		}		
	}
	
	/**
	 * Process event click button Cancel
	 * */
	public void btnCancel_OnClick(View view){
        startActivity(new Intent(DisplayTextRecievedActivity.this, MainActivity.class));
	}

}
