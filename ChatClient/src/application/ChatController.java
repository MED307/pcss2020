package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.dataTypes.ChatMessage;
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

// Class for the chatroom
public class ChatController extends Controller implements Initializable{
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		chatDisplayList.setCellFactory(chatRoomListView -> new ChatListCellController());
		
		EventHandler filter = new EventHandler<MouseEvent>()
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
	
	private boolean isChatting;
	
	
	private Thread chatThread = new Thread() {													// A Thread for updating the chat, looking for new imputs from server to display. 
		public void run() {
			isChatting = true;																	// Sets a bool true
			System.out.println("Test thread");
			
			while(isChatting) {																	// While bool is true, loop to keep running while chat is active
				try {
					chatDisplayList.getItems().add((ChatMessage)getConnection().receive());		// Recieves new items from server to list
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	private String roomID = getUser().getCurrentChatRoom().getChatId();							// Sets the roomID
	
	// Method for loading chat
	public void loadChat() 
	{
		for(ChatMessage e: getUser().getCurrentChatRoom().getMessages()) 						// For loop, that adds messages to chat
		{
			addMessage(e);																		// Runs the  addMessage method
		}
	}
	

	// Method makes the button interactable
	public void sendbtn(ActionEvent event) {
		sendMessage();																			// runs the Send method 
	}
	
	// Method makes the 'Enter' key interactable
	public void sendField(KeyEvent keyEvent) {
				
		if (keyEvent.getCode() == KeyCode.ENTER) {												// If key is "Enter"
			sendMessage();																		// runs the Send method 
		}
	}

	// Method for sending a message
	public void sendMessage() {
		String roomID = getUser().getCurrentChatRoom().getChatId();
		ChatMessage message = new ChatMessage(chatField.getText(), getUser().getId(), roomID);	
		
		if (message.getMessage() != null) { 								// If the text field is not empty.
			chatField.clear();												// Clears text field
			this.chatDisplayList.getItems().add(message);					// Adds message to chat 
			chatDisplayList.scrollTo(chatDisplayList.getItems().size());	// Scrolls to the bottom
			getUser().getCurrentChatRoom().addMessage(message);				// 
			try {
				getConnection().send(message);								// Sends the message to server.
			} catch (Exception e) {

				e.printStackTrace();
			}
			
		}
		
		else {
			System.out.println("No message sent. Message was: " + message);
		}
	}
	
	// 
	public void addMessage(ChatMessage msg)
	{
		try {
			getConnection().send(msg);	//Sends the ChatMessage object to server.
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	this.chatDisplayList.getItems().add(msg); // Displays the mesage on listView
	}
	
	
	// Method to go back to chat server list.
	public void goBack(ActionEvent event)
	{
		try {
			changeScene(event, "ChatSelector.fxml", getUser(), getConnection());	//Change scene to chat room selection
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
