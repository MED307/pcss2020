import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
	private Socket client;
	private BufferedReader input;
	private PrintWriter output;
	UserDataBase udb = new UserDataBase();
	ChatroomManager crm = new ChatroomManager();
	
	ObjectInputStream oisin = new ObjectInputStream(client.getInputStream());
	ObjectOutputStream oosout = new ObjectOutputStream(client.getOutputStream());
	
	public ClientHandler(Socket clientSocket) throws IOException {
		this.client = clientSocket;
		input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		//output = new PrintWriter(client.getOutputStream());
		
	}
	//receives string(creating a user), object user, og et chatroom, message
	@Override
	public void run() {
		try {
			while(true) {
				//for the users
				Object something = oisin.readObject();
				//check user login
				if(something instanceof ArrayList<?>) {
					String ushername = (String) ((ArrayList<?>)something).get(0);
					String password = (String) ((ArrayList<?>)something).get(1);
					int userIndex = 0;
					boolean login = false;
					for(int i= 0 ; i > udb.reUsers().size() ; i++ ) {
						if(udb.reUsers().get(i).getUsername().compareTo(ushername) == 0) {
							userIndex = i;
						}
						if(udb.reUsers().get(userIndex).getPassword().compareTo(password) == 0 ) {
							login = true;
							oosout.writeObject(udb.reUsers().get(userIndex));
							break;
						} else {
							login = false;
							System.out.println("wrong username/password");
							//oisout.writeBoolean(login);
						}
					}
				}//login done
				
				//creates a new user
				if(something instanceof String) {
					String usercheck = ((String)something);
					boolean check = false;
					for(int i= 0 ; i > udb.reUsers().size() ; i++ ) {
						if(udb.reUsers().get(i).getUsername().compareTo(usercheck) == 0) {
							check = true;
							oosout.writeBoolean(check);
							break;
						}else {
							check = false;
							oosout.writeBoolean(check);
						}
					}
				}
				//adds the new user to the list
				if(something instanceof User) {		
					udb.addUser((User)something);
					System.out.println("user added");
				} 
				//chatrooms0
				Object chat = oisin.readObject();
				boolean chatcheck = false;
				if(chat instanceof ChatMessage){
					for(int i= 0 ; i > crm.getChatrooms().size() ; i++ ) { //going through the chatroom IDs
						if(crm.getChatrooms().get(i).getChatId() == ((ChatMessage)chat).getRoomID()) {// if the messages ID matches the Room ID
							chatcheck = true;
							crm.getChatrooms().get(i).addMessage((ChatMessage)chat);  //Adds a message to the chatroom
						}
					}
				}
				if(chat instanceof Chatroom) {
					
				}
				
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			output.close();
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
