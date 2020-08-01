
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginPageController {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXButton loginbutton;

    
    @FXML
    private JFXPasswordField pass;

    

    @FXML
    private JFXButton forgot;

    String target;

	private int flag_name;
    
    @FXML
    void open_conf(ActionEvent event) {
    	if(!(Pattern.matches("[a-z]{3,15}", name.getText()))) {
    		flag_name=1;
    		Alert a=new Alert(AlertType.ERROR);
    		a.setContentText("enter name and click forgot password");
    		a.showAndWait();
    	
    	}
        
    	else {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Text Input Dialog");
    	dialog.setHeaderText("What's your favourite holiday spot?");
    	dialog.setContentText("Please enter your :");
    	
    	
    	Optional<String> result = dialog.showAndWait();
    	if(result.isPresent())
    	{
    		target=result.get();
    	}
    	System.out.print(target);
    	try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/hall","root","");  
			
			
			PreparedStatement statement = con.prepareStatement("select * from admin where name = ? and security=?");    
			statement.setString(1, name.getText());
			statement.setString(2, target);
			ResultSet rs = statement.executeQuery();
			
System.out.println("\n heyhey");
       while(rs.next())
       {

      	   System.out.print(rs.getString(1));
       	// System.out.println(rs.getString(1));
    	   Alert alert=new Alert(AlertType.INFORMATION);
    	   alert.setContentText("Your password is "+rs.getString(3)+"\n If you want you can \n change the password");
    	   alert.showAndWait();
       }
       con.close();
    }
    catch(Exception e)
    	{
    	e.getMessage();
    	}
    }
    }
    @FXML
    void open_main(ActionEvent event) 
    {	
    	try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/hall","root","");  
			
			
			PreparedStatement statement = con.prepareStatement("select * from admin where name = ? and pass=?");    
			statement.setString(1, name.getText());
			statement.setString(2, pass.getText());
			ResultSet rs = statement.executeQuery();
			
System.out.println("\n hey bro");
       while(rs.next())
       {
    	   Alert alert=new Alert(AlertType.INFORMATION);
    	   alert.setContentText("LOGIN SUCCESSFULL");
    	   alert.showAndWait();
       }
       con.close();
		Stage stage = (Stage) loginbutton.getScene().getWindow();
	    stage.close();
    	Stage primaryStage=new Stage();
    	try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("second.fxml");
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
    catch(Exception e)
    	{
    	e.getMessage();
    	}
    }

    	
    }
