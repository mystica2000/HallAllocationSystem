import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignUpController {
	
	
	ObservableList<String> deptname= FXCollections.observableArrayList("IT","CSE","ECE","EEE","MECH","CIVIL");
	
    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField contact;

    @FXML
    private JFXButton Signup;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField fid;

    @FXML
    private ChoiceBox<String> choicedept;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXPasswordField pass1;


	@FXML
    private JFXTextField spots;
    
    
    private int flag_name;

	private int flag_email;

	private int flag_pass;

	private int flag_facid;

	private int flags;
	

	@FXML
	 void initialize()
	 {
		choicedept.setItems(deptname);
		choicedept.setValue("valuebox");
	 }
	
	
	 @FXML
	    void initialze(MouseEvent event) {
		 System.out.print("bello");
	    }
	
    @FXML
    void open_signUp(ActionEvent event) {
    	if(Pattern.matches("[A-Za-z]{3,15}", name.getText())) {
    		flag_name=1;
    	}
        if((pass.getText()).equals(pass1.getText()))
        {
        	flag_pass=1;
            final String PASSWORD_PATTERN = 
                    "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

        	if(Pattern.matches(PASSWORD_PATTERN,pass.getText()))
    		{
        		flags=1;
    		}

        }
        if(Pattern.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", email.getText()))
        		{
        	flag_email=1;
        		}
        if(Pattern.matches("^[0-9]{2}[A-Za-z]{3}[0-9]{3}", fid.getText())) {
        	flag_facid=1;
        }
        
        if(flag_name==1 && flag_email==1 && flag_facid==1 && flag_pass==1 && flags==1)
        {
        	System.out.println("\n AWEOSME MYSTICA");
        	
        	try{  
        	
        		Class.forName("com.mysql.cj.jdbc.Driver");  
        		Connection con=DriverManager.getConnection(  
        		"jdbc:mysql://localhost:3306/hall","root","root");  
        		String sql="insert into admin"+"(fid,name,pass,email,dept,security)"+ "values (?,?,?,?,?,?)";
        		PreparedStatement mystmt = null;
        		mystmt=con.prepareStatement(sql);
        		String fids=fid.getText();
        		String names=name.getText();
        		String passs=pass.getText();
        		String emails=email.getText();
        		String choicedepts=choicedept.getValue();
        		String spot=spots.getText();
        		System.out.print("ehaaa");
        		
        		System.out.println("\n NAME:"+names+"\n pass:"+passs+" sd ca"+emails+"\n asd"+choicedepts+"\n hef"+spot);
        		
        		mystmt.setString(1,fids);
        		mystmt.setString(2,names);
        		mystmt.setString(3, passs);
        		mystmt.setString(4,emails);
        		mystmt.setString(5, choicedepts);
        		mystmt.setString(6, spot);
        		mystmt.execute();
        		con.close();  
        		

        		Stage stage = (Stage) Signup.getScene().getWindow();
        	    stage.close();
            	Stage primaryStage=new Stage();
            	try {
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
        			
            	Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Results:");
                alert.setContentText("SignUp Successfull!");
         
                alert.showAndWait();
        		}catch(SQLIntegrityConstraintViolationException e){ 
        
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error Dialog");
        			alert.setHeaderText("Look, an Error ");
        			alert.setContentText("Ooops, there was an error in username..!"+"\n Try Again");

        			alert.showAndWait();
        		}
        	catch(Exception e)
        	{
        		System.out.println(e.getMessage());
        	}
        }
        else
        {
        	Alert a=new Alert(AlertType.ERROR);
        	a.setContentText("MISSING REQUIREMENT FILL"+"\n CHECK AND TRY AGAIN");
        	a.showAndWait();
        }
    }

}
