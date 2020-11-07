package application;

import java.io.IOException;

import application.dataTypes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
	
	private User user;
	private Connection connection;
	
	// Method for changing a scene
	public void changeScene(ActionEvent event, String FXML, User user, Connection connection) throws IOException
	{
		
		//creates the FXMLLoader used to load the next scene
		FXMLLoader loader = new FXMLLoader();
		
		//Sets the location of where the FXML file is
		loader.setLocation(getClass().getResource(FXML));
		
		//loads the fXML file
		AnchorPane chatRoot = (AnchorPane)loader.load();
		
		//creates a scene from the FXML file
		Scene chat = new Scene(chatRoot);				
		
		//add the Stylesheet to the scene
		chat.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
		//gets the current stage
		Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		
		//sets the scene of that stage
		window.setScene(chat);
		
		//gets the controller of the FXML file
		Controller controller = (Controller) loader.getController();
		
		//pass the User and connection to the next scene
		controller.setUser(user);
		controller.setConnection(connection);
		
		//check which scene and controller it is
		if (FXML.compareTo("ChatSelector.fxml") == 0)
		{
			
			//loads the ChatRoom list
			ChatSelectorController sController = (ChatSelectorController) loader.getController();
			sController.loadChatrooms();
		}
		else if(FXML.compareTo("Chat.fxml") == 0) 
		{
			//loads the Chat history
			ChatController cController = (ChatController) loader.getController();
			cController.setRoomNametxt(user.getCurrentChatRoom().getChatroomName());
			cController.loadChat();
		}
		window.show();
	}
	
	// Method for changing a scene with mouse event
	public void changeScene(MouseEvent event, String FXML, User user, Connection connection) throws IOException
	{
		
		//creates the FXMLLoader used to load the next scene
		FXMLLoader loader = new FXMLLoader();
		
		//Sets the location of where the FXML file is
		loader.setLocation(getClass().getResource(FXML));
		
		//loads the fXML file
		AnchorPane chatRoot = (AnchorPane)loader.load();
		
		//creates a scene from the FXML file
		Scene chat = new Scene(chatRoot);
		
		//add the Stylesheet to the scene
		chat.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//gets the current stage
		Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		
		//sets the scene of that stage
		window.setScene(chat);
		
		//gets the controller of the FXML file
		Controller controller = (Controller) loader.getController();
		
		//pass the User and connection to the next scene
		controller.setUser(user);
		controller.setConnection(connection);
		
		//check which scene and controller it is
		if (FXML.compareTo("ChatSelector.fxml") == 0)
		{
			
			//loads the ChatRoom list
			ChatSelectorController sController = (ChatSelectorController) loader.getController();
			sController.loadChatrooms();
		} 
		else if(FXML.compareTo("Chat.fxml") == 0) 
		{
			
			//loads the Chat History
			ChatController cController = (ChatController) loader.getController();
			cController.setRoomNametxt(user.getCurrentChatRoom().getChatroomName());
			cController.loadChat();
		}
		window.show();
	}

	// Getters and setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
