package application;


import java.net.URL;
import java.util.ResourceBundle;

import application.dataTypes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewUserController extends Controller implements Initializable{
	
	
	// FXML Imports
	@FXML
	private TextField newUserNameInput;
	
	@FXML
	private PasswordField newPassWordInput;
	
	@FXML  
	private Button newCreateUserBtn;
	
	@FXML
	private Text popErrorNameTxt;
	

	// Method for creating new user
	public void createNewUser(ActionEvent event)
	{
		
		//checks if username og password input fields are empty, if show display error
		if (newUserNameInput.getText().trim().isEmpty() && newUserNameInput.getPromptText() != null || newPassWordInput.getText().trim().isEmpty() && newPassWordInput.getPromptText() != null)
		{
			popErrorNameTxt.setText("Missing input");
			popErrorNameTxt.setVisible(true);
			System.out.println("Error");
		}
		
		//else if not
		else if (this.newUserNameInput.getText() != null && this.newUserNameInput.getText() != null) 
		{
			try 
			{
				System.out.println("User saved: " + this.newUserNameInput.getText()+ " | " + this.newPassWordInput.getText());
				
				//creates a new user
				User user = new User(this.newUserNameInput.getText(),this.newPassWordInput.getText());
				
				//sends user to server
				getConnection().send(user);
				
				//closes the popup window
				((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		}
	}
	
	//display if the user already exists (Not implemented)
	public void userExists()
	{
		popErrorNameTxt.setText("Username already taken");
		popErrorNameTxt.setVisible(true);
	}
	
	
	//make the error message not show for the user
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		// TODO Auto-generated method stub
		popErrorNameTxt.setVisible(false);
	}

}
