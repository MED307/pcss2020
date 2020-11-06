package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.dataTypes.Chatroom;
import application.dataTypes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;


public class ChatSelectorController extends Controller implements Initializable{
	
	// Importing and instantiating the FXML variables 
	@FXML
	private ListView<String> listview;
	
	@FXML
	private Button createRoomBtn;
	
	@FXML
	private Button logOutBtn;
	
	String currentItemSelected;

	
	// Initialize, in which list view is selectable
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
				
		listview.setOnMouseClicked(new EventHandler<MouseEvent>() {

		    @Override
		    public void handle(MouseEvent click) {

		        if (click.getClickCount() == 2) {
		           currentItemSelected = listview.getSelectionModel().getSelectedItem();
		           try {
		        	   
		        	   try {
						getConnection().send(currentItemSelected);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	   
		        	   Object requested = null;
		        	   while (!(requested instanceof Chatroom)) {
		        		   try {
							requested = getConnection().receive();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	   
		        	   }
		        	   getUser().setCurrentChatRoom((Chatroom)requested);
		        	   changeScene(click, "Chat.fxml", getUser(), getConnection());
		           } catch (IOException e) {
					// TODO Auto-generated catch block
		        	   e.printStackTrace();
		           }
		        }
		    }
		});


	}
	
	// Method for loading chatrooms.
	public void loadChatrooms() 
	{
		for(String i: this.getUser().getChatRooms())
		{
			listview.getItems().add(i);
		}
	}
	



	// Method for creating a new chatroom, opens popup.
	public void createRoomBtn(ActionEvent event) throws IOException {
		
		System.out.println("Room btn works");
		UserPopUp pop = new UserPopUp();
		ArrayList<String> outcome = pop.displayChatroom("new chatroom", "PopUps/newChatroom.fxml");
		String roomName = outcome.get(0);
		ArrayList<String> newChatUser = new ArrayList<>();
		for (int i = 1; i < outcome.size()-1; i++) {
			newChatUser.add(outcome.get(i));
		}

		Chatroom ctm = new Chatroom(getUser(),roomName, newChatUser);
		listview.getItems().add(roomName);
		try {
			getConnection().send(ctm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method for loggin out.
	public void logOut(ActionEvent event)
	{
		try {
			changeScene(event, "Client.fxml", getUser(), getConnection());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
