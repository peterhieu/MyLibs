package com.example.common;

import java.util.ArrayList;

import com.example.datadiagramsocket.MainActivity;
import com.example.datadiagramsocket.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends Activity {
	
	public TextView m_SystemTimeTextView;
	public ListView m_SystemTimeListView;
	public Button m_CancelButton;
	
	//int counter = 8;
	//String st="Counter";
	int clickCounter=0;
	ArrayList<String> listItems=new ArrayList<String>();
	ArrayAdapter<String> adapter;
	//String[] FRUITS = new String[] { "Quận 1", "Quận 2", "Quận 3",
    //    "Quận 4", "Quận 5", "Quận 6", "Quận 7", "Quận 8"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_screen);
		
		m_SystemTimeListView = (ListView)findViewById(R.id.lvListSystemTime);  
		
		
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item, listItems);
		
		m_SystemTimeListView.setAdapter(adapter);
		
		m_SystemTimeListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // When clicked, show a toast with the TextView text
            //	st=st+counter;
            //	FRUITS[counter]=new String(st);
            	
            	//adapter.insert(st,8);
            	//m_SystemTimeListView.invalidate();
                Toast.makeText(getApplicationContext(),
                ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });  
	}
	public void btnCancel_OnClick(View view){
		/*Intent i= new Intent(DisplayActivity.this,MainActivity.class);
		startActivity(i);
		finish();*/
		listItems.add("Clicked : "+clickCounter++);
        adapter.notifyDataSetChanged();
	}
}
