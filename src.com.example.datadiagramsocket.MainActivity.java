package com.example.datadiagramsocket;

import com.example.common.AlertDialogError;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	public EditText m_IPAddressEditText;
	public EditText m_PortEditText;
	public Button 	m_ReceiveButton;
	public Button 	m_QuitButton;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_screen);
		
		m_IPAddressEditText = (EditText)findViewById(R.id.edtIPAddress);
		m_PortEditText 		= (EditText)findViewById(R.id.edtPort);
	}
	
	public void btnReceive_OnClick(View view){
		
		String ip 	= m_IPAddressEditText.getText().toString();
		String port = m_PortEditText.getText().toString();
		
		if (ip.equals("")) {
			new AlertDialogError (this,"Error","No ip address");
		}
		else if (port.equals("")) {
			new AlertDialogError (this,"Error","No data port");
		}
		else {	
			
			Bundle bndl = new Bundle();
			bndl.putCharSequence("ip", ip);
			bndl.putCharSequence("port", port);
			
//			Intent i= new Intent(MainActivity.this,DisplayActivity.class);	
//			i.putExtra("intentFromMain", bndl);			
//			startActivity(i);

			Intent i2= new Intent(MainActivity.this,DisplayTextRecievedActivity.class);	
			i2.putExtra("intentFromMain", bndl);			
			startActivity(i2);
			
			finish();
		}		
		
	}
	
	public void btnQuit_OnClick(View view){
		finish();
	}
	

}
