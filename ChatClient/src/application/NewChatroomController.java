package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewChatroomController extends Controller {
	
	// FXML Imports
	@FXML
	private Button createChatBtn;
	
	@FXML
	private TextField chatName;
	
	@FXML
	private TextField desiredUser;
	
	@FXML
	private Text infoText;
	
	@FXML
	private Text popErrorRoomTxt;
	
	private String newChatName;
	
	private String newChatUsers;
	

	//method that runs when the user clicks on the createChatBtn
	public void create(ActionEvent event)
	{
		//checks if chatName is empty if so display error
		if (chatName.getText().trim().isEmpty()) {
			System.out.println("Error");
			infoText.setVisible(false);
			popErrorRoomTxt.setText("Error: Missing room name");
			popErrorRoomTxt.setVisible(true);
		}
		
		//checks if desiredUser is empty if so display error
		if (desiredUser.getText().trim().isEmpty()) {
			System.out.println("Error");
			infoText.setVisible(false);
			popErrorRoomTxt.setText("Error: Missing users");
			popErrorRoomTxt.setVisible(true);
			
		}
		
		//if both have text in them
		else if (this.chatName.getText() != null && this.desiredUser.getText() != null) 
		{
			//sets the NewChatName and NewChatUsers to the text inputed
			this.setNewChatName(this.chatName.getText());
			this.setNewChatUsers(this.desiredUser.getText());
			
			//closed the popup window
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
			
		}
		


	}
	
	//display if the room already exists (Not implemented)
	public void roomExists()
	{
		popErrorRoomTxt.setText("Error: Room name already exist");
		popErrorRoomTxt.setVisible(true);
	}
	
	
	//getters and setters
	public String getNewChatName() {
		return newChatName;
	}

	public void setNewChatName(String newChatName) {
		this.newChatName = newChatName;
	}

	public String getNewChatUsers() {
		return newChatUsers;
	}

	public void setNewChatUsers(String newChatUsers) {
		this.newChatUsers = newChatUsers;
	}
	
	
}
