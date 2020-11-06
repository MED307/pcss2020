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
	private NetworkConnection connection;
	
	// Method for changing a scene
	public void changeScene(ActionEvent event, String FXML, User user, NetworkConnection connection) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();														 
		loader.setLocation(getClass().getResource(FXML));
		AnchorPane chatRoot = (AnchorPane)loader.load();
		Scene chat = new Scene(chatRoot);																	
		chat.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(chat);
		Controller controller = (Controller) loader.getController();
		controller.setUser(user);
		controller.setConnection(connection);
		if (FXML.compareTo("ChatSelector.fxml") == 0)
		{
			ChatSelectorController sController = (ChatSelectorController) loader.getController();
			sController.loadChatrooms();
		}
		else if(FXML.compareTo("Chat.fxml") == 0) 
		{
			ChatController cController = (ChatController) loader.getController();
			cController.setRoomNametxt(user.getCurrentChatRoom().getChatroomName());
			cController.loadChat();
		}
		window.show();
	}
	
	// Method for changing a scene with mouse event
	public void changeScene(MouseEvent event, String FXML, User user, NetworkConnection connection) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(FXML));
		AnchorPane chatRoot = (AnchorPane)loader.load();
		Scene chat = new Scene(chatRoot);
		chat.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(chat);
		Controller controller = (Controller) loader.getController();
		controller.setUser(user);
		controller.setConnection(connection);
		if (FXML.compareTo("ChatSelector.fxml") == 0)
		{
			ChatSelectorController sController = (ChatSelectorController) loader.getController();
			sController.loadChatrooms();
		} 
		else if(FXML.compareTo("Chat.fxml") == 0) 
		{
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


	public NetworkConnection getConnection() {
		return connection;
	}


	public void setConnection(NetworkConnection connection) {
		this.connection = connection;
	}
}
