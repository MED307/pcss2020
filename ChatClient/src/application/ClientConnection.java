package application;

import java.io.Serializable;
import java.util.function.Consumer;

public class ClientConnection extends NetworkConnection {

	private String ip;
	private int port;
	
	public ClientConnection(String ip, int port, Consumer<Serializable> onReceiveCallback) {
		super(onReceiveCallback);
		this.ip = ip;
		this.port = port;
		
	}


	// Get IP
	@Override
	protected String getIP() {
		return ip;
	}

	// Get Port
	@Override
	protected int getPort() {
		return port;
	}

}
