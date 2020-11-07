package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class ChatRoomListCellController extends ListCell<String>{

	@FXML
	Label chatRoomName;
	
	@FXML
	Label chatRoomUsers;
	
	@FXML
	AnchorPane pane;
	
	FXMLLoader mLLoader;
	
	@Override
    protected void updateItem(String string, boolean empty) 
	{
        super.updateItem(string, empty);
        
        if(empty || string == null) 
        {

            setText(null);
            setGraphic(null);

        } 
        else 
        {
        	setText(string.split("(?<=\\D)(?=\\d)")[0]);
        }
        	
       }
}
