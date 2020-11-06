package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewChatroomController extends Controller {
	
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
	

	
	public void create(ActionEvent event)
	{
		if (chatName.getText().trim().isEmpty()) {
			System.out.println("Error");
			infoText.setVisible(false);
			popErrorRoomTxt.setText("Error: Missing room name");
			popErrorRoomTxt.setVisible(true);
		}
		
		if (desiredUser.getText().trim().isEmpty()) {
			System.out.println("Error");
			infoText.setVisible(false);
			popErrorRoomTxt.setText("Error: Missing users");
			popErrorRoomTxt.setVisible(true);
			
		}
		
		else if (this.chatName.getText() != null && this.desiredUser.getText() != null) 
		{
			this.setNewChatName(this.chatName.getText());
			this.setNewChatUsers(this.desiredUser.getText());
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
			
		}
		


	}
	
	public void roomExists()
	{
		popErrorRoomTxt.setText("Error: Room name already exist");
		popErrorRoomTxt.setVisible(true);
	}

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
