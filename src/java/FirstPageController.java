import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstPageController {

    @FXML
    private JFXButton signup;

    @FXML
    private JFXButton signin;
    
    @FXML
    private JFXButton github;

    @FXML
    private JFXButton trouble;
    
	@FXML
    void open_signin(ActionEvent event) {
    	
		Stage stage = (Stage) signup.getScene().getWindow();
	    stage.close();
		
    	Stage primaryStage=new Stage();
    	try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("login.fxml");
	            if (url == null) {
	                System.out.println("Can't load FXML file");
	                Platform.exit();
	            }
	           
	    		primaryStage.setTitle("ExamHallAllocation");
	        primaryStage.setScene(new Scene(FXMLLoader.load(url)));
	        primaryStage.show();
	        //old.close();
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	
    }

    @FXML
    void open_signup(ActionEvent event) {

		Stage stage = (Stage) signin.getScene().getWindow();
	    stage.close();
    	Stage primaryStage=new Stage();
    	try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("signup.fxml");
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
    void open_trouble(ActionEvent event) throws IOException {
    	if (Desktop.isDesktopSupported()) {
    	    Desktop desktop = Desktop.getDesktop();
    	    if (desktop.isSupported(Desktop.Action.MAIL)) {
    	        URI mailto = null;
				try {
					mailto = new URI("mailto:mysticainf@gmail.com?subject=ERROR%20REPORT");
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	        desktop.mail(mailto);
    	    }
    	}
    }
    
    
    
    @FXML
    void open_github(ActionEvent event) {
    	try {
    	    Desktop.getDesktop().browse(new URL("https://github.com/mystica2000").toURI());
    	} catch (Exception e) {}
    }


}
