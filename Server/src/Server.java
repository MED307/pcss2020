import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	static ArrayList<ClientHandler> clients = new ArrayList<>();
	private Excecutor
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		
		while(true) {
			System.out.println("Server sprinting");
			Socket client = serverSocket.accept(); //listener thread
			System.out.println("Client connected");
			ClientHandler clientThread = new ClientHandler(client);
			clients.add(clientThread);
		}
		PrintWriter output = new PrintWriter(client.getOutputStream()true);
		BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		new Thread(	() )
	}

}
