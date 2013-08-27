package com.example.datadiagramsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class DataDiagramSocket implements Runnable{
	
	private static DataDiagramSocket 	m_instance;
	private static int 					m_defPort;
	private static InetAddress			m_defIPAddress;
	private DatagramSocket 				m_datagramSocket;
	private DatagramPacket 				m_datagramPacket;
	
	private DataDiagramSocket()throws SocketException, UnknownHostException {
		
		setPort(Integer.parseInt(DisplayTextRecievedActivity.port));
		setBindAddress(InetAddress.getByName(DisplayTextRecievedActivity.ip));
		
	}
	
	public static DataDiagramSocket getInstance() {
		
		if(m_instance == null) {
			try {				
				m_instance = new DataDiagramSocket();		
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				Log.d("m_instance = new DataDiagramSocket", "exception");
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				Log.d("m_instance = new DataDiagramSocket", "exception");
				e.printStackTrace();
			}
		}	
		
		return m_instance;
	 }
	
	public final static void setPort(int port) {
		// data in : Port value to connect UDP
		m_defPort = port;
	}
	
	public final static void setBindAddress(InetAddress ipAddr) {
		// data in : IP Address value to connect UDP
		m_defIPAddress = ipAddr;
	}
	
	public final int receiveDatagram(DatagramPacket dgram) {
		//data in: DatagramPacket to receive with length of buffer
		//data out : return Length of buffer which be received
		return dgram.getLength();
	}
	
	public static String stringFromPacket(DatagramPacket packet) {
		//data in  : DatagramPacket for converting to string   
		//return data out : String result from DatagramPacket
		return new String(packet.getData(), 0, packet.getLength());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {	
				
				byte[] 	message 		= new byte[256];
				m_datagramPacket 		= new DatagramPacket(message, message.length); 
				m_datagramSocket 		= new DatagramSocket(m_defPort, m_defIPAddress);
				
				m_datagramSocket.setSoTimeout(10000);			
				m_datagramSocket.receive(m_datagramPacket);
				
				DisplayTextRecievedActivity.handerThr.post(new DisplayTextRecievedActivity.UpdateThread(m_datagramPacket.getData()));				
				m_datagramSocket.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			} 		    	
		}	
	}
	
	public InetAddress get_ip() {
		return m_defIPAddress;
	}
	
	public int get_port() {
		return m_defPort;
	}
}
