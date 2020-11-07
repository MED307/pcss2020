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
		
		//Tells the listview to use the ChatListCellController and corresponding FXML file as the way to display the information it receives
		listview.setCellFactory(chatRoomListView -> new ChatRoomListCellController());
		
		//adds a double click function to the list view to load the chatroom clicked on
		listview.setOnMouseClicked(new EventHandler<MouseEvent>() {

		    @Override
		    public void handle(MouseEvent click) {

		    	//check that there has been clicked twice
		        if (click.getClickCount() == 2) {
		        	
		        	//finds the item clicked on
		           currentItemSelected = listview.getSelectionModel().getSelectedItem();
		           try {
		        	   
		        	   //tries to send the clicked item to the server
		        	   try {
		        		   getConnection().send(currentItemSelected);
		        	   } catch (Exception e) {
		        		   e.printStackTrace();
		        	   }
		        	   
		        	   System.out.println("Beginning");
		        	   
		        	   // waits for a chatroom to be send back
		        	   Object requested = null;
		        	   
		        	   //as long as the object received is not a chatroom wait for it again
		        	   while (!(requested instanceof Chatroom)) {
		        		   System.out.println("Changing");
		        		   try {
		        			   requested = getConnection().receive();
		        		   } catch (Exception e) {
		        			   e.printStackTrace();
		        		   }   
		        	   }
		        	   
		        	   
		        	   //set the received chatroom as the currentChatroom and goes to next scene to display it
		        	   getUser().setCurrentChatRoom((Chatroom)requested);
		        	   System.out.println("Changed");
		        	   changeScene(click, "Chat.fxml", getUser(), getConnection());
		        	   
		           } catch (IOException e) {
		        	   e.printStackTrace();
		           }
		        }
		    }
		});


	}
	
	// Method for loading chatrooms.
	public void loadChatrooms() 
	{
		
		//for each chatroom ID the user has
		for(String i: this.getUser().getChatRooms())
		{
			
			//stops the same chatroom to be displayed twice
			if (listview.getItems().size() == 0 || i.compareTo(listview.getItems().get(listview.getItems().size()-1)) != 0)
			{
				
				//adds the ID to the listview
				listview.getItems().add(i);
			}
			
		}
	}
	



	// Method for creating a new chatroom, opens popup.
	public void createRoomBtn(ActionEvent event) throws IOException {
		
		//creates new popUp
		UserPopUp pop = new UserPopUp();
		
		//displays the popup for creating a chatroom 
		ArrayList<String> outcome = pop.displayChatroom("new chatroom", "PopUps/newChatroom.fxml");
		
		//takes the first element and uses it as the name of the room
		String roomName = outcome.get(0);
		
		//creates list of added users
		ArrayList<String> newChatUser = new ArrayList<>();
		for (int i = 1; i < outcome.size(); i++) {
			newChatUser.add(outcome.get(i));
		}
		
		//creates the chatroom and sends it to the server, aswell as displays it in the client
		Chatroom ctm = new Chatroom(getUser(),roomName, newChatUser);
		System.out.println(newChatUser);
		listview.getItems().add(ctm.getChatId());
		getUser().addChatRoom(ctm.getChatId());
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
