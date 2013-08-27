package com.example.common;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class AlertDialogError {
	
	public AlertDialogError (final Context context, String title, String msgContent) {
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

		// Setting Dialog Title
		alertDialog.setTitle(title);
		
		// Setting Dialog Message
		alertDialog.setMessage(msgContent);
				
		// Setting OK Button
		alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	        	//Execute after dialog closed
	        	Toast.makeText(context, "You clicked on OK", Toast.LENGTH_SHORT).show();
	        } 
		});

		// Showing Alert Message
		alertDialog.show();				
	}
}
