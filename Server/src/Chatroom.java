import java.util.ArrayList;
import java.util.Random;

public class Chatroom {
	
	// The name of the chatroom
	private String name;
	
	// The id for the chatroom 
	private int chatId;
	
	// An ArrayList that stores all of the users in the chatroom
	private ArrayList<User> users = new ArrayList<>();
	
	// A User object that stores the user that created the chatroom
	private User creator;
	
	// a ChatHistory object used to store the chat history of the chatroom
	private ChatHistory chatHistory;
	
	// The constructor for the Chatroom class that creates a Chatroom where the user creating the chatroom is assigned the creator 
	// and where the chatroom is given a specific name
	public Chatroom(User user, String name){
		
		this.creator = user;
		this.name = name;
		
		// A random integer is assigned as the id for the chatroom
		Random rd = new Random();
        this.chatId = rd.nextInt();
	}
	
	// Method that returns the name of the chatroom
	public String getChatroomName() {
		
		return this.name;
		
	}
	
	// Method that returns the unique id of the chatroom
	public int getChatId() {
		
		return chatId;
	}
	
	// Method that returns the name of the creator of the chatroom 
    public String getCreatorName() {
    	
		return this.creator.getUsername();
		
	}
    
    // Method that returns of list of usernames of the users in the chatroom
    public ArrayList<String> getUsernames() {
    	
    	
    	// An ArrayList for the usernames is created
    	ArrayList<String> usernames = new ArrayList<>();
    	
    	// A for each loop goes through the user and gets their usernames
    	for(User i: users) {
    		usernames.add(i.getUsername());
    	}

 		return usernames;
 		
 	}
	
    // Method that is used to display a message
	public String displayMessage(User user, String message) {
		
		return user.getUsername() + ": " + message;
		
	}
	
	// Method that is used to send a message
	public void sendMessage(String message) {
		
		//chatHistory.add(displayMessage());
		
	}
}
