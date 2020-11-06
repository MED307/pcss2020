package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.dataTypes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// ClientController serves as the main login screen
public class ClientController extends Controller implements Initializable{
	
	
	// Imports FXML variables
	@FXML
	private TextField userNameInput;
	
	@FXML
	private TextField passWordInput;
	
	@FXML
	private Button loginBtn;
	
	@FXML
	private Button createUserBtn;
	
	@FXML
	private Text invalidLoginTxt;
	
	private String userName;
	
	private String passWord;
	
	private ArrayList<String> userData = new ArrayList<>();
	
	// Method for loggin in
	public void login(ActionEvent event)
	{
		
		this.userName = userNameInput.getText();
		this.passWord = passWordInput.getText();
		
		if (userNameInput.getText().trim().isEmpty() || passWordInput.getText().trim().isEmpty()) {				// Display error if name or password field are empty
			System.out.println("Error in loggin");																// Sys write for debug
			invalidLoginTxt.setText("invalidLoginTxt");															// Set display error text
			invalidLoginTxt.setVisible(true);																	// Show Error text
		}
		
		else if (userName != null && passWord != null) {														// If fields are not empty
			try {
							
				getConnection().send(userData);																	// Sends the userdata to server
				Object received = getConnection().receive();													// recieve object
				
				if(received instanceof User) {																	// If its of object: user
					getConnection().receive();																	
					try {
						changeScene(event, "ChatSelector.fxml", getUser(), getConnection());					// Change scene to chat selection as user.
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// Method for opening the popup window for creating a new user
	public void createNewuser(ActionEvent event) throws IOException
	{
		UserPopUp pop = new UserPopUp();												// Create popup
		pop.setConnection(getConnection());												// Pass the connection
		pop.displayUser("new User", "PopUps/newUser.fxml");								// Dispay the popup
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
