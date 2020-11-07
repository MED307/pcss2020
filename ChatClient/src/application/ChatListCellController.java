package application;

import java.io.IOException;

import application.dataTypes.ChatMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

// Class that determines how messages that gets added to a chat's listview.
public class ChatListCellController extends ListCell<ChatMessage>{
	
	// FXML Imports
	@FXML
	Label userName;
	
	@FXML
	Label message;
	
	@FXML
	AnchorPane pane;
	
	FXMLLoader mLLoader;
	
	//overrides the standard way of displaying things in a listcell to use the ChatListCell.FXML and controller instead
	@Override
    protected void updateItem(ChatMessage msg, boolean empty) 
	{
        super.updateItem(msg, empty);
        
        //checks that the message to be displayed is not null
        if(empty || msg == null) 
        {

            setText(null);
            setGraphic(null);

        } 
        else 
        {
        	//adds this as the controller for ChatListCell.FXML
        	if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ChatListCell.fxml"));
                mLLoader.setController(this);
                
                //tries to load the FXML
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        	
        	//remove everything after the first digit in the ID so only the name is displayed
        	userName.setText(msg.getUser().split("(?<=\\D)(?=\\d)")[0] + ": ");
        	message.setText(msg.getMessage());
        	
        	//makes the pane which contains the fxml elements the graphic that is shown
        	setGraphic(pane);
        	
        }
	}
}
