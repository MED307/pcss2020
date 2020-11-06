package application;

import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {
	
	private Consumer<Serializable> onReceiveCallback;
	private ConnectionThread connThread = new ConnectionThread();
	
	
	
	public NetworkConnection(Consumer<Serializable> onReceiveCallback) 
	{
		this.onReceiveCallback = onReceiveCallback;
		connThread.setDaemon(true);
	}

	public void startConnection() throws Exception 
	{
		connThread.start();
	}
	
	public void send(Serializable data) throws Exception 
	{
		connThread.out.writeObject(data);
	}
	
	public void closeConnection() throws Exception
	{
		connThread.socket.close();
	}
	
	public Object receive() throws Exception
	{
		
		if(connThread.in.readObject() instanceof Object) {
			return connThread.in.readObject();	
		}
		
		else {
			return null;
		}
	}
	


	protected abstract String getIP();
	protected abstract int getPort();

	private class ConnectionThread extends Thread
	{
		private Socket socket;
		private ObjectOutputStream out;
		private ObjectInputStream in;

		@Override
		public void run()
		{
			try(
				Socket socket = new Socket(getIP(),getPort());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
				{
					this.socket = socket;
					this.out = out;
					this.in = in;
					socket.setTcpNoDelay(true);
					
					while (true) {
						Serializable data = (Serializable) in.readObject();
					}
				}

			catch (Exception e) {

			}


		}
	}

}
