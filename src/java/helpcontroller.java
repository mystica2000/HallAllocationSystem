import java.net.URL;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class helpcontroller {

    @FXML
    private JFXButton nextone;

    @FXML
    private JFXButton close;

    @FXML
    private Text sethelp;
    
    
    @FXML
    private Text title;

    @FXML
    void initialize()
    {
    	sethelp.setText("UPLOAD 4 FILES\r\n" + 
    			"Students  Format  -  year_id,year_id,year_id,no\r\n" + 
    			"Staff  Format       - faculty_id,faculty_name,faculty_email,years_of_experience          \r\n" + 
    			"\r\n" + 
    			"ExamSchedule Format  -  Date,Subject_id,Subject_id,Subject_id                     \r\n" + 
    			" Note:Fill as NULLS for subjects not conducted on that day\r\n" + 
    			"\r\n" + 
    			"Example: 01.01.2020 15IT501 15IT601 NULLS\r\n" + 
    			"\r\n" + 
    			"ExamHall Format - HallNo,HallCapacity\r\n" + 
    			"\r\n" + 
    			"Wait for minute to process !!!\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"Click View Files to view details and print as pdf"
    			+ "");
    }
    
    
    @FXML
    void closethis(ActionEvent event) {
    	Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    	
    }

    @FXML
    void open_next(ActionEvent event) {
    	title.setText("Mark Statement");	
    	sethelp.setText("Upload file in the format \n"
    				+ "\n DATE,COURSEID,FACULTYID,COURSENAME \n"
    		     	+ "\n enter your password of your email \n"
    				+ "\n then send the report \n");
    	
    }

}
