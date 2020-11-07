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
	
	private Connection connection;																		// Network connection
	
	
	// Start that creates the primary stage
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();														// New loader
			loader.setLocation(getClass().getResource("Client.fxml"));									// Uses the resources of the Client.fxml file
			AnchorPane root = (AnchorPane)loader.load();												// 
			Controller controller = (Controller) loader.getController();								// gets the controller from loader
			connection = createClient();
			controller.setConnection(connection);														// Sets connection
			Scene login = new Scene(root);																// Sets the first scene to login.
			login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());		// Adds the .css file
			primaryStage.setScene(login);																// Sets the stage scene to login
			primaryStage.show();																		// Shows the stage
			primaryStage.setResizable(false);															// Removes option to resize and maximize window
		
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
