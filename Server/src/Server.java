import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	public static ArrayList<ClientHandler> clients = new ArrayList<>();
	private static final int PORT = 8000;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		
		while(true) {
			System.out.println("Server sprinting");
			Socket client = serverSocket.accept(); //listener thread
			System.out.println("Client connected");
			ClientHandler clientThread = new ClientHandler(client);
			clients.add(clientThread);
			System.out.println(clients.size());
		}
		//PrintWriter output = new PrintWriter(client.getOutputStream(), true);
		//BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}

}
