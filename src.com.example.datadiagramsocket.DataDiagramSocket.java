package com.example.datadiagramsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.content.Intent;
import android.util.Log;


public class DataDiagramSocket {
	
	private static DataDiagramSocket m_instance = null;	//Singleton instance of DataDiagramSocket
	private static int m_defPort;					//Port to connect UDP
	private static InetAddress m_defIPAddress;		//IP Address to connect UDP
	private DatagramSocket m_datagramSocket;		//Used to receive System Time data from Windows Simulation via UDP
	private DatagramPacket m_datagramPacket;		//Used to store system time package
	
	
	private DataDiagramSocket() {}
	
	public final static synchronized DataDiagramSocket getInstance() {
		//data out: DataDiagramSocket Singleton 		
		if(m_instance == null) {
			m_instance = new DataDiagramSocket();
		}	
		return m_instance;
	 }
	
	public final static void setPort(int port) {
		// data in : Port value to connect UDP
		m_defPort=port;
	}
	
	public final static void setBindAddress(InetAddress ipAddr) {
		// data in : IP Address value to connect UDP
		m_defIPAddress=ipAddr;
	}
	
	

	public final int receiveDatagram(DatagramPacket dgram){
		//data in: DatagramPacket to receive with length of buffer
		//data out : return Length of buffer which be received
		return dgram.getLength();
	}
	
	public static String stringFromPacket(DatagramPacket packet) {
		//data in  : DatagramPacket for converting to string   
		// return data out : String result from DatagramPacket
		 return new String(packet.getData(), 0, packet.getLength());
	}
	
	public static void processUDP() {
		String textReceivedFromServer;
		
	    
	    try {
	    			    	
	    	byte[] message = new byte[50];
	    	
	    	InetAddress inetServer = InetAddress.getByName("10.0.2.15");
		
	    	DatagramPacket p = new DatagramPacket(message, message.length); 

			DatagramSocket s = new DatagramSocket(5000, inetServer);

			s.setSoTimeout(20000);
			
			s.receive(p); 
			
			textReceivedFromServer = new String(message, 0, p.getLength());
			
		    Log.d("Udp tutorial","message:" + textReceivedFromServer);		

		    //**********************************************************************	
		    
/*			Intent i= new Intent(SettingUDP.this, DisplayTextRecievedActivity.class);	
			i.putExtra("textReceived", textReceivedFromServer);
			startActivity(i);
*/			
			s.close();
			 
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}		
	

}
