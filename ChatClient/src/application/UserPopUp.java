package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


// class to create and handle popUp windows
public class UserPopUp {
	
	private Connection connection;
	
	//display for new user creation
	public void displayUser(String title, String FXML) throws IOException
	{
		
		//Creates new Stage (window)
		Stage window = new Stage();
		
		//new FXMLLoader for the stage
		FXMLLoader loader = new FXMLLoader();
		
		//Tell the loader where to find the FXML File
		loader.setLocation(getClass().getResource(FXML));
		
		//Blocks events from being delivered to any other application window.
		window.initModality(Modality.APPLICATION_MODAL);
		
		//loads the FXML file
		AnchorPane root = (AnchorPane)loader.load();
		
		//creates new scene
		Scene popUp = new Scene(root);
		
		//apply styleSheet 
		popUp.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//stops the user for resizing
		window.setResizable(false);
		
		//Sets the scene for the window
		window.setScene(popUp);
		
		//sets the title for the window
		window.setTitle(title);
		
		//sets minimum width
		window.setMinWidth(250);
		
		//shows the window
		window.show();
		
		//gets the controller
		Controller controller = (Controller) loader.getController();
		
		//forwards the connection to the popup
		controller.setConnection(connection);
		
	}
	
	
	//getter
	public Connection getConnection() {
		return connection;
	}

	//setter
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
	//display for new chatRoom creation
	public ArrayList<String> displayChatroom(String title, String FXML) throws IOException
    {
		
		//creates list to store outCome
		ArrayList<String> outCome = new ArrayList<>();
		
		//Creates new Stage (window)
        Stage window = new Stage();
        
        //new FXMLLoader for the stage
        FXMLLoader loader = new FXMLLoader();
        
     	//Tell the loader where to find the FXML File
        loader.setLocation(getClass().getResource(FXML));
        
        //Blocks events from being delivered to any other application window.
        window.initModality(Modality.APPLICATION_MODAL);
        
        //loads the FXML file
        AnchorPane root = (AnchorPane)loader.load();
        
        //creates new scene
        Scene popUp = new Scene(root);
        
        //apply styleSheet
        popUp.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        //stops the user for resizing
        window.setResizable(false);
        
        //Sets the scene for the window
        window.setScene(popUp);
        
        //sets the title for the window
        window.setTitle(title);
        
        //sets minimum width
        window.setMinWidth(250);
        
        //shows the popUp and freezes the other window, until it is closed
        window.showAndWait();

        //if FXML is that of newChatroom.fxml
        if (FXML.compareTo("PopUps/newChatroom.fxml") == 0)
        {
        	
        	//gets the controller of the popup
            NewChatroomController controller = loader.getController();
            
            //gets the outcome from the popUp
            outCome.add(controller.getNewChatName());
            
            //creates an ArrayList, by splitting the string into indicidual users
            ArrayList<String> users = new ArrayList<String>(Arrays.asList(controller.getNewChatUsers().split(", ")));
            
            //adds the user to the outcome
            outCome.addAll(users);
        }
        
        //returns the outcome
        return outCome;
    }
}
