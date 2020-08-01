import java.net.URL;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
public class Main extends Application {

	
	public void start(Stage primaryStage) {
		try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("first.fxml");
	            if (url == null) {
	                System.out.println("Can't load FXML file");
	                Platform.exit();
	            }
	            System.out.print("heeyy");
	    		primaryStage.setTitle("ExamHallAllocation");
	        primaryStage.setScene(new Scene(FXMLLoader.load(url)));
	        Screen screen = Screen.getPrimary();
	        Rectangle2D bounds = screen.getVisualBounds();
	        	        primaryStage.show();
	        }
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}	
	}

    public static void main(String[] args) {
        launch(args);
    }
}
