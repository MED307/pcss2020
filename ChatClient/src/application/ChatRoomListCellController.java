package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

//Class that determines how messages that gets added to the chatroom listview.
public class ChatRoomListCellController extends ListCell<String>{

	// FXML Imports
	@FXML
	Label chatRoomName;
	
	@FXML
	Label chatRoomUsers;
	
	@FXML
	AnchorPane pane;
	
	FXMLLoader mLLoader;
	
	
	//overrides the standard way of displaying things in a listcell to use the ChatListCell.FXML and controller instead
	@Override
    protected void updateItem(String string, boolean empty) 
	{
        super.updateItem(string, empty);
        
        //checks that the message to be displayed is not null
        if(empty || string == null) 
        {

            setText(null);
            setGraphic(null);

        } 
        else 
        {
        	//remove everything after the first digit in the ID so only the name is displayed
        	setText(string.split("(?<=\\D)(?=\\d)")[0]);
        }
        	
       }
}
