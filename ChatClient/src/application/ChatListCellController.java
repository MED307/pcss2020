package application;

import java.io.IOException;

import application.dataTypes.ChatMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class ChatListCellController extends ListCell<ChatMessage>{
	
	@FXML
	Label userName;
	
	@FXML
	Label message;
	
	@FXML
	AnchorPane pane;
	
	FXMLLoader mLLoader;
	
	@Override
    protected void updateItem(ChatMessage msg, boolean empty) 
	{
        super.updateItem(msg, empty);
        
        if(empty || msg == null) 
        {

            setText(null);
            setGraphic(null);

        } 
        else 
        {
        	if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ChatListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        	
        	userName.setText(msg.getUser().getUsername() + ": ");
        	message.setText(msg.getMessage());
        	
        	setGraphic(pane);
        	
        }
	}
}
