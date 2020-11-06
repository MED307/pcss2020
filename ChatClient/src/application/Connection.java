package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	String host;
	int port;
	Socket socket;
	private ConnectionThread connThreadOut;
	private ConnectionThread connThreadIn;
	Object received;
	Object sendObj = null;
	
	Connection(String host, int port) throws UnknownHostException, IOException 
	{
		socket = new Socket(host,port);
		this.host = host;
		this.port = port;
		connThreadOut = new ConnectionThread(true);
		connThreadOut.start();
		connThreadIn = new ConnectionThread(false);
		connThreadIn.start();
		
	}
	
	public void send(Object obj) 
	{
		connThreadOut.send(obj);
	}
	
	public Object receive() throws ClassNotFoundException, IOException 
	{
		return received;
	}
	
	private class ConnectionThread extends Thread
	{
		boolean isOut;
		private ObjectOutputStream out;
		private ObjectInputStream in;
		
		ConnectionThread(boolean bool)
		{
			isOut = bool;
		}

		@Override
		public void run()
		{
			System.out.println("Running");
			try {
				if(isOut) {
					out = new ObjectOutputStream(socket.getOutputStream());
				} 
				else
				{
					in = new ObjectInputStream(socket.getInputStream());
				}
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
						Serializable data = (Serializable) in.readObject();
						received = data;
					} 
					else
					{

							//out.writeObject(sendObj);
					}
				}
				catch(Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		
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
