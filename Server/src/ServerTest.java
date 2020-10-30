//Inspiration taken from: http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServerTest implements Runnable {

	protected int serverPort = 8000;
	protected ServerSocket serverSocket = null;
	protected boolean isStopped = false;
	protected Thread runningThread = null;
	
	public ServerTest(int port) {
		this.serverPort = port;
	}
	
	public void run() {
		synchronized(this) {
			this.runningThread = Thread.currentThread();
		}
		
		openServerSocket();
		while(! isStopped()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				if(isStopped()) {
			System.out.println("The server is stopped!");
			return;
		}
		throw new RuntimeException(
				"Could not accept client connection", e);
	}
	new Thread(
			new WorkerRunnable(
					clientSocket, "Server Test")
			).start();
	   }
System.out.println("The server is stopped");
	}

private synchronized boolean isStopped() {
	return this.isStopped;
}

public synchronized void stop() {
	this.isStopped = true;
	try {
		this.serverSocket.close();
	} catch(IOException e) {
		throw new RuntimeException("Could not close server",e);
	}
}

private void openServerSocket() {
	try {
		this.serverSocket = new ServerSocket(this.serverPort);
	} catch(IOException e) {
		throw new RuntimeException("Cannot open this port",e);
	}
}

}


/*  Put this into the main method
 * 
MultiThreadedServer server = new MultiThreadedServer(9000);
new Thread(server).start();

try {
Thread.sleep(20 * 1000);
} catch (InterruptedException e){
   e.printStackTrace();
}
System.out.println("Stopping the server");
server.stop();
 */





