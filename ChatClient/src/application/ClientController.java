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
	
	Object received;
	
	// Method for logging in
	public void login(ActionEvent event)
	{
		
		//gets the input from the textFields
		this.userName = userNameInput.getText();
		this.passWord = passWordInput.getText();
		
		//checks whether they are empty
		if (userNameInput.getText().trim().isEmpty() || passWordInput.getText().trim().isEmpty()) {
			System.out.println("Error in loggin");
			
			//displays to user that the login is not possible
			invalidLoginTxt.setText("invalidLoginTxt");
			invalidLoginTxt.setVisible(true);
		}
		else if (userName != null && passWord != null) {
			try {
				
				//creates an ArrayList and sends it to user
				userData.add(userName);
				userData.add(passWord);
				getConnection().send(userData);
				
				
				//waits for answer from server
				while(!(received instanceof User)) {
					received = getConnection().receive();
				}
				
				//if answer is instance of user set is as user
				if(received instanceof User) {														
					setUser((User)received);
					try {
						//change scene to display chatrooms that user is part of
						changeScene(event, "ChatSelector.fxml", getUser(), getConnection());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Works");
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// Method for opening the popup window for creating a new user
	public void createNewuser(ActionEvent event) throws IOException
	{
		//creates a popup to make a new user
		UserPopUp pop = new UserPopUp();
		
		//forward the connection to the popup
		pop.setConnection(getConnection());
		
		//display the popUp
		pop.displayUser("new User", "PopUps/newUser.fxml");
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
