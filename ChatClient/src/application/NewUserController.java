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
		
		if (newUserNameInput.getText().trim().isEmpty() && newUserNameInput.getPromptText() != null || newPassWordInput.getText().trim().isEmpty() && newPassWordInput.getPromptText() != null){
			popErrorNameTxt.setText("Missing input");
			popErrorNameTxt.setVisible(true);
			System.out.println("Error");
		}
		
		
		else if (this.newUserNameInput.getText() != null && this.newUserNameInput.getText() != null) 
		{
			try {
				getConnection().send(newUserNameInput.getText());
				
				boolean nameTaken = false;

				if (nameTaken != true ) {
					System.out.println("User saved: " + this.newUserNameInput.getText()+ " | " + this.newPassWordInput.getText());
					User user = new User(this.newUserNameInput.getText(),this.newPassWordInput.getText());
					getConnection().send(user);
					((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
				}
				
				else {
					popErrorNameTxt.setText("Username already taken");
					popErrorNameTxt.setVisible(true);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	public void userExists()
	{
		popErrorNameTxt.setText("Username already taken");
		popErrorNameTxt.setVisible(true);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		popErrorNameTxt.setVisible(false);
	}

}
