package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

// Main class
public class Main extends Application {
	
	
	// Initializing variables
	private boolean isServer = false;														// Boolean to server/client test. 
	
	private NetworkConnection connection = createClient(); 									// Network connection check return form method
	
	
	// Start that creates the primary stage
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();														// New loader
			loader.setLocation(getClass().getResource("Client.fxml"));									// Uses the resources of the Client.fxml file
			AnchorPane root = (AnchorPane)loader.load();												// 
			Controller controller = (Controller) loader.getController();								// gets the controller from loader
			controller.setConnection(this.connection);													// Sets connection
			Scene login = new Scene(root);																// Sets the first scene to login.
			login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());		// Adds the .css file
			primaryStage.setScene(login);																// Sets the stage scene to login
			primaryStage.show();																		// Shows the stage
			primaryStage.setResizable(false);															// Removes option to resize and maximize window
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() throws Exception {
		connection.startConnection();
	}
	
	public void stop() throws Exception {
		connection.closeConnection();
	}
	
	// Method for creating a client connection
	private ClientConnection createClient() {
		return new ClientConnection("127.0.0.1", 55555, data -> {									// Sets client connection to ip and port
			Platform.runLater(()-> {
				
				System.out.println("Create Client Test");											// Sys write for debug
			});
		});
	}
	

	// Main method
	public static void main(String[] args) {
		launch(args);

	}
}
