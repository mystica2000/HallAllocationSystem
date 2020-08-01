import java.net.URL;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPageController {

    @FXML
    private JFXButton upload_files;

    @FXML
    private JFXButton allocate;

    @FXML
    private JFXButton profile;

    @FXML
    void open_allocate(ActionEvent event) {

    	Stage stage = (Stage) allocate.getScene().getWindow();
        stage.close();
    	Stage primaryStage=new Stage();
    	try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("hall.fxml");
	            if (url == null) {
	                System.out.println("Can't load FXML file");
	                Platform.exit();
	            }
	           
	    		primaryStage.setTitle("ExamHallAllocation");
	        primaryStage.setScene(new Scene(FXMLLoader.load(url)));
	        primaryStage.show();
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	
    }

    @FXML
    void open_browse(ActionEvent event) {
      	allocate.setDisable(false);
        
    	Stage primaryStage=new Stage();
    	try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("system.fxml");
	            if (url == null) {
	                System.out.println("Can't load FXML file");
	                Platform.exit();
	            }
	           
	    		primaryStage.setTitle("ExamHallAllocation");
	        primaryStage.setScene(new Scene(FXMLLoader.load(url)));
	        primaryStage.show();
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
    }	

    @FXML
    void open_profile(ActionEvent event) {

    	//Stage stage = (Stage) profile.getScene().getWindow();
       // stage.close();
    	Stage primaryStage=new Stage();
    	try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("profile.fxml");
	            if (url == null) {
	                System.out.println("Can't load FXML file");
	                Platform.exit();
	            }
	           
	    		primaryStage.setTitle("ExamHallAllocation");
	        primaryStage.setScene(new Scene(FXMLLoader.load(url)));
	        primaryStage.show();
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}

    }

}
