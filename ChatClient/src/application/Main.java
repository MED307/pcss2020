package application;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

// Main class
public class Main extends Application {
	
	private Connection connection;
	
	
	// Start that creates the primary stage
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//creates the FXMLLoader used to load the next scen
			FXMLLoader loader = new FXMLLoader();
			
			//Sets the location of where the FXML file is
			loader.setLocation(getClass().getResource("Client.fxml"));
			
			//loads the fXML file
			AnchorPane root = (AnchorPane)loader.load();
			
			//gets the controller of the FXML file
			Controller controller = (Controller) loader.getController();
			
			//creates the connection to the server
			connection = createClient();
			
			//set the connection to the connection of the scene being loaded
			controller.setConnection(connection);
			
			//creates a scene from the FXML file
			Scene login = new Scene(root);
			
			//add the Stylesheet to the scene
			login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//sets the scene of the stage
			primaryStage.setScene(login);
			
			//shows the stage
			primaryStage.show();
			
			//stops the user from resizing the window
			primaryStage.setResizable(false);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Method for creating a client connection
	private Connection createClient() throws UnknownHostException, IOException {
		System.out.println("Done");
		return new Connection("localhost",55555);
	}
	

	// Main method
	public static void main(String[] args) {
		launch(args);

	}
}
