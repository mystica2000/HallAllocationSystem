import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 



public class Uploadschedule {

    @FXML
    private JFXButton profile;

    @FXML
    private JFXButton second;

    @FXML
    private JFXTextField second_file;

    @FXML
    private JFXButton go;

	private int flag2;

	private int flag3;


	private int flag1;

	private String[] temps;

	private int k;

	private String[] temps0;

	private String[] temps1;

	private String[] temps2;

	private String[] temps3;
	

	 
	
	
    @FXML
    void browse_second(ActionEvent event) {
    	FileChooser filechoose=new FileChooser();
		filechoose.setTitle("Select XSLX file");
		filechoose.setInitialDirectory(new File("D:\\"));
		filechoose.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("Excel files", "*.xlsx"));
	    File selectedItem;
		selectedItem=filechoose.showOpenDialog(null);
		second_file.setText(selectedItem.getAbsolutePath());
		flag3=1;
    }



    @FXML
    void open_profile(ActionEvent event) {
    	Stage primaryStage=new Stage();
    	try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("help.fxml");
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
    void verify_report_eligible(ActionEvent event) {
    	if(flag3==1)
    	{
    		write_to_fourth(second_file.getText());
    		Stage primaryStage=new Stage();
        	try {
    	        /*Loading the fxml file for the first page*/
    	    		URL url = getClass().getResource("report.fxml");
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
    	else
    	{
    		Alert a=new Alert(AlertType.ERROR);
    		a.setContentText("select files properly");
    		a.showAndWait();
    	}
    }

	private void write_to_fourth(String aa) {
		// TODO Auto-generated method stub
		try {
            FileInputStream excelFile = new FileInputStream(new File(aa));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            System.out.println("hello qorl");
            while (iterator.hasNext()) {
           	 String[] temp=new String[5];
                int i=0;
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
               	 
                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                   	 temp[i]=new String();
                        temp[i]=currentCell.getStringCellValue();
                        i++;
                    }

                }
                try{  
                	System.out.println("\n hello");
               	 Class.forName("com.mysql.cj.jdbc.Driver");  
               	 Connection con=DriverManager.getConnection(  
               	 "jdbc:mysql://localhost:3306/hall","root","root");  
               	 String sql="insert into second"+"(edate,cid,fid,cname)"+ "values (?,?,?,?)";
               	 PreparedStatement mystmt = null;
            	 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            	 Date dt= sdf.parse(temp[0]);
            	 java.sql.Date sqlDate = new java.sql.Date(dt.getTime());

               	 mystmt=con.prepareStatement(sql);
               	  mystmt.setDate(1,sqlDate);
              	 mystmt.setString(2,temp[1]);
               	mystmt.setString(3,temp[2]);
               	mystmt.setString(4,temp[3]); 
               	 k++;
               	 mystmt.execute();
               	 con.close();  
               	 }catch(Exception e){ 
               		 e.printStackTrace();
               		 e.getMessage();
               	 }  
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
   	 Alert a=new Alert(AlertType.CONFIRMATION);
   	 a.setContentText("Inserted Successfully");
   	 a.showAndWait();
		//	sendemail();
	}

	


	}
	
	
	
	
