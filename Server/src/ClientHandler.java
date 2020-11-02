import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {
	private Socket client;
	private BufferedReader input;
	private PrintWriter output;
	
	public ClientHandler(Socket clientSocket) throws IOException {
		this.client = clientSocket;
		input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		output = new PrintWriter(client.getOutputStream());
	}
	
	
}
