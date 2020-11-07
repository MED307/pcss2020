package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.dataTypes.ChatMessage;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

// Controller for CHAT.FXML
public class ChatController extends Controller implements Initializable{
	
	
	//When the Controller and FXML is Loaded this codes run immediatly
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Tells the listview to use the ChatListCellController and corresponding FXML file as the way to display the information it receives
		chatDisplayList.setCellFactory(chatRoomListView -> new ChatListCellController());
		
		
		//stops the user from being able to click on the chat history, by consuming the event
		EventHandler<MouseEvent> filter = new EventHandler<MouseEvent>()
			{
		    	public void handle(MouseEvent event) {
		    		event.consume();
		    	}
			};
		chatDisplayList.addEventFilter(MouseEvent.MOUSE_PRESSED, filter);

	}

	//FXML imports 
	@FXML
	private ListView<ChatMessage> chatDisplayList;
	
	@FXML
	private Button sendbtn;

	@FXML
	private Button exitChatbtn;
	
	@FXML
	private TextArea chatField;
	
	@FXML
	private Text roomNametxt;
	
	
	//thread to continuosly check if a new Message has been received
	private Thread chatThread = new Thread() {													 
		public void run() {																	
			
			while(!chatThread.interrupted()) {																	
				try {
					
					//checks what have been send
					Object object = getConnection().receive();
					
					//checks whether received object is of type ChatMessage
					if (object instanceof ChatMessage)
					{
						
						//checks if the message is not the exact same as the previous message to prevent double posting
						if (chatDisplayList.getItems().size() == 0 || !((ChatMessage) object).equals(chatDisplayList.getItems().get(chatDisplayList.getItems().size()-1)) && ((ChatMessage) object).getRoomID().compareTo(getUser().getCurrentChatRoom().getChatId()) == 0) 
						{
							
							//adds the message to the listview for the user to see
							ChatMessage chatMessage = (ChatMessage) object;
							Platform.runLater(() ->chatDisplayList.getItems().add(chatMessage));
							sleep(10);
	
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("Done");
		}
	};
	
	// Method for loading chat into the chatDisplayList
	public void loadChat() 
	{
		
		//loops through all messages in the chatroom
		for(ChatMessage e: getUser().getCurrentChatRoom().getMessages()) 						
		{
			this.chatDisplayList.getItems().add(e); // Displays the mesage on listView
		}
		
		//starts the thread to check for incoming messages
		chatThread.start();
	}
	

	// Method that is run when the FXML Button sendbtn is pressed
	public void sendbtn(ActionEvent event) {
		sendMessage();
	}
	
	// Method that is run when the "Enter" key is pressed
	public void sendField(KeyEvent keyEvent) {
				
		if (keyEvent.getCode() == KeyCode.ENTER) {
			sendMessage();
		}
	}

	//Method for sending a message
	public void sendMessage() {
		
		//creates a object of type ChatMessage to be send to the server
		String roomID = getUser().getCurrentChatRoom().getChatId();
		ChatMessage message = new ChatMessage(chatField.getText(), getUser().getId(), roomID);	
		
		//check if a message has been written
		if (message.getMessage() != null) { 
			
			//clears the chatfield
			chatField.clear();
			
			//scrolls to the buttom of the chatDisplayList to always display the newest messages
			chatDisplayList.scrollTo(chatDisplayList.getItems().size());
			
			//tries to send the message to server, if the server and client is connected
			try {
				getConnection().send(message);
			} catch (Exception e) {

				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("No message sent. Message was: " + message);
		}
	}
	
	// Method to go back to chat server list.
	public void goBack(ActionEvent event)
	{
		//stops the message checker thread
		chatThread.interrupt();
		
		//tries to change scene
		try {
			changeScene(event, "ChatSelector.fxml", getUser(), getConnection());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Method for getting chat room name
	public Text getRoomNametxt() {
		return roomNametxt;
	}

	// Method for setting chat room name
	public void setRoomNametxt(String roomNametxt) {
		this.roomNametxt.setText(roomNametxt);
	}
	

}
