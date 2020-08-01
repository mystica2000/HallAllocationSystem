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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class ProfilePageController {

    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXPasswordField pass1;

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField fid;

    @FXML
    private JFXTextField contact;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton profile;
    
    int flag_name=0;
    int flag_pass=0;
    int flag_email=0;
    int flag_facid=0;

	private String target;
    
    @FXML
    void initialize()
    {
     	    	TextInputDialog dialog = new TextInputDialog("");
    	dialog.setTitle("Text Input Dialog");
    	dialog.setHeaderText("what's your faculty id?");
    	dialog.setContentText("Please enter your id:");

    	// Traditional way to get the response value.
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    		 target = result.get();
    	
    	
    	try{  
    		Class.forName("com.mysql.cj.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/hall","root","root");  
    		String sql="select * from admin where fid=?";
    		
    	     PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, target);
            ResultSet rs = statement.executeQuery();
 
    		
    		
    		 System.out.println("\n heyhey");
       while(rs.next())
       {
    	   fid.setText(rs.getString(1));
    	   user.setText(rs.getString(2));
    	   pass.setText(rs.getString(3));
    	   email.setText(rs.getString(4));
    	   contact.setText(rs.getString(5));
    	   
    	}


    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    	}
    	else
    	{
    		Stage stage = (Stage) save.getScene().getWindow();
    	     stage.close();
    	    	
    	}
    	
    }
    
    
    
    @FXML
    void allow_edit(ActionEvent event) {
    	user.setEditable(true);
    	pass.setEditable(true);
    	pass1.setEditable(true);
    	email.setEditable(true);
    	contact.setEditable(true);

    }

    @FXML
    void open_cancel(ActionEvent event) {

    	Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
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
    void update_changes(ActionEvent event) {
    	if(Pattern.matches("[a-z]{3,15}", user.getText())) {
    		flag_name=1;
    	}
        if((pass.getText()).equals(pass1.getText()))
        {
        	flag_pass=1;
        }
        if(Pattern.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", email.getText()))
        		{
        	flag_email=1;
        		}
        if(Pattern.matches("^[0-9]{2}[A-Za-z]{3}[0-9]{3}", fid.getText())) {
        	flag_facid=1;
        }
        if(flag_name==1 && flag_email==1 && flag_facid==1 && flag_pass==1)
        {
        	Alert a=new Alert(AlertType.INFORMATION);
    		a.setContentText("Inserted Sucessufully");
    		a.showAndWait();
    		
        	try{  
        		Class.forName("com.mysql.cj.jdbc.Driver");  
        		Connection con=DriverManager.getConnection(  
        		"jdbc:mysql://localhost:3306/hall","root","root");  
        		String sql="update admin set name=?,pass=?,email=?,security=? where fid=?";
        		
        		
       	     PreparedStatement mystmt = con.prepareStatement(sql);
             	
        		mystmt.setString(1,user.getText());
        		mystmt.setString(2,pass.getText());
        		mystmt.setString(3,email.getText());
        		mystmt.setString(4,contact.getText());

        		 mystmt.setString(5, target);
                 
        		mystmt.execute();

        		con.close();  
        		Alert a1=new Alert(AlertType.INFORMATION);
        		a1.setContentText("Inserted Sucessufully");
        		a1.showAndWait();
        		}catch(Exception e){ System.out.println(e);}  
        }
        else
        {
        	Alert a=new Alert(AlertType.ERROR);

    		a.setHeaderText("ERROR CANT UPDATE");
        	if(flag_name==0)
        	{
        		a.setContentText("NAME ERROR");
        	}
        	if(flag_email==0)
        	{
        		a.setContentText("INCORRECT EMAIL");
        	}
        	if(flag_facid==0)
        	{
        		a.setContentText("INCORRECT FACULTY ID");
        	}
        	if(flag_pass==0)
        	{
        		a.setContentText("PASSWORD MISMATCH");
        	}
        	a.showAndWait();
        }
    }

}
