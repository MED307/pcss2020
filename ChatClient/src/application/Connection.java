package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	
	//variables
	String host;
	int port;
	Socket socket;
	
	//threads to receive and send objects from the server
	private ConnectionThread connThreadOut;
	private ConnectionThread connThreadIn;
	
	Object received;
	
	
	//constructor
	Connection(String host, int port) throws UnknownHostException, IOException 
	{
		socket = new Socket(host,port);
		this.host = host;
		this.port = port;
		
		//creates and starts the threads
		connThreadOut = new ConnectionThread(true);
		connThreadOut.start();
		connThreadIn = new ConnectionThread(false);
		connThreadIn.start();
		
	}
	
	
	//method to pass and object to the thread to send it to the server
	public void send(Object obj) 
	{
		connThreadOut.send(obj);
	}
	
	
	//method to retrieve the information send from the server
	public Object receive() throws ClassNotFoundException, IOException 
	{
			return received;

	}
	
	
	//nested class for the threads
	private class ConnectionThread extends Thread
	{
		
		//needed to stop the threads to create multiple in and output as that will create an StreamCorruptedException
		boolean isOut;
		
		//empty in- and Output stream
		private ObjectOutputStream out;
		private ObjectInputStream in;
		
		
		//Constructor determines if it is the thread for sending or receiving 
		ConnectionThread(boolean bool)
		{
			isOut = bool;
		}
		
		//run method which runs when the thread is started
		@Override
		public void run()
		{
			System.out.println("Running");
			try {
				if(isOut) {
					
					//creates the outputStream from the socket so it is connected to the server
					out = new ObjectOutputStream(socket.getOutputStream());
				} 
				else
				{
					//creates the InputStream from the socket so it is connected to the server
					in = new ObjectInputStream(socket.getInputStream());
				}
				//disables Nagle's algorithm, to reduce latency between send packages, making messages and other object arrive faster
				socket.setTcpNoDelay(true);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			while (true) 
			{
				try 
				{
					if(!isOut) {
						
						//waits for data to arrive and prints them to the received variables
						Serializable data = (Serializable) in.readObject();
						System.out.println(data.toString());
						received = data;

					} 
				}
				catch(Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		
		//sends the object to the server
		public void send(Object obj) 
		{
			try {
				out.writeObject(obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
