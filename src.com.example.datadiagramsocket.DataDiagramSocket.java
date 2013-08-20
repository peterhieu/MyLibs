package com.example.datadiagramsocket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DataDiagramSocket {
	private static DataDiagramSocket m_instance;
	private static int m_defPort;
	private static InetAddress m_defIPAddress;
	private DatagramSocket m_datagramSocket;
	private DatagramPacket m_datagramPacket;
	private DataDiagramSocket()throws SocketException, UnknownHostException {
		
	}
	public final static synchronized DataDiagramSocket getInstance()
	 {
		//data out: DataDiagramSocket Singleton 
		return m_instance;//kiem tra lai
	 }
	public final static void setPort(int port) {
		// data in : Port value to connect UDP
	}
	public final static void setBindAddress(InetAddress ipAddr) {
		// data in : IP Address value to connect UDP
	}
	public final int receiveDatagram(DatagramPacket dgram){
		//data in: DatagramPacket to receive with length of buffer
		return 0;//data out : return Length of buffer which be received
	}
	public static String stringFromPacket(DatagramPacket packet) {
		//data in  : DatagramPacket for converting to string   
		// return data out : String result from DatagramPacket
		return "dg";//kiem tre lai
	}
	
	

}
