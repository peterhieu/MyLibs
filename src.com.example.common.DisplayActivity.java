package com.example.common;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import com.example.datadiagramsocket.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayActivity extends Activity {
	
	public TextView 		m_SystemTimeTextView;
	public ListView 		m_SystemTimeListView;
	public Button 			m_CancelButton;
	static Handler 			handerThr;
	
	ArrayList<String> 		dataItem;
	ArrayAdapter<String> 	adapter;
	String					ip;
	String					port;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_screen);
		
		Intent i 	= getIntent();
		Bundle bdl 	= i.getBundleExtra("intentFromMain");
		ip 			= bdl.getString("ip", "10.0.2.15");
		port 		= bdl.getString("port", "6000");
		
		
		m_SystemTimeListView = (ListView)findViewById(R.id.lvListSystemTime);  
		dataItem 			= new ArrayList<String>();		
		adapter 			= new ArrayAdapter<String>(this,R.layout.single_list_item, dataItem);
		
		m_SystemTimeListView.setAdapter(adapter);
		
		handerThr = new Handler();		
		new Thread(new ThreadSocket()).start();
    		
	}

	/**
	 * ThreadSocket process connect to server through udp socket 
	 * */	
	public class ThreadSocket implements Runnable{
		@Override
		public void run() {
			while(true){
				try {	
					
					byte[] 			message = new byte[127];
					DatagramPacket 	p 		= new DatagramPacket(message, message.length); 
					DatagramSocket	s 		= new DatagramSocket(Integer.parseInt(port),
																InetAddress.getByName(ip));
					
					s.setSoTimeout(10000);			
					s.receive(p);
					handerThr.post(new UpdateThread(p.getData()));
							 
				} catch (IOException e) {
					e.printStackTrace();
				} 		    	
			}	
		}			
	}

	/**
	 * class UpdateThread
	 * */
	public class UpdateThread implements Runnable{

		String msg;
		
		UpdateThread(byte[] Message){
			msg=new String(Message, 0, Message.length);
		}
		
		@Override
		public void run() {
			if(msg!=null){
				dataItem.add(msg);
		        adapter.notifyDataSetChanged();
			}	
		}
		
	}
	
	/**
	 * Process event click button Cancel
	 * */
	public void btnCancel_OnClick(View view){
        
	}	
}
