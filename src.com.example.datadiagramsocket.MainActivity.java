package com.example.datadiagramsocket;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	public EditText m_IPAddressEditText;
	public EditText m_PortEditText;
	public Button m_ReceiveButton;
	public Button m_QuitButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void btnReceive_OnClick(){}
	private void btnQuit_OnClick(){}

}
