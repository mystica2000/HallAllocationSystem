

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.jfoenix.controls.JFXButton;
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

public class BrowsepageController {

    @FXML
    private JFXButton student;

    @FXML
    private JFXButton profile;

    @FXML
    private JFXButton hall;

    @FXML
    private JFXButton faculty;

    @FXML
    private JFXTextField student_text;

    @FXML
    private JFXTextField hall_text;

    @FXML
    private JFXTextField faculty_text;

    @FXML
    private JFXButton generate;

    @FXML
    private JFXTextField exam_text;

    @FXML
    private JFXButton exam;


    public int flag1,flag2,flag3,flag4;

	private int yoe;
    
    @FXML
    void open_examfile(ActionEvent event) {
	   	FileChooser filechoose=new FileChooser();
		filechoose.setTitle("Select XSLX file");
		filechoose.setInitialDirectory(new File("D:\\"));
		filechoose.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("Excel files", "*.xlsx"));
	    File selectedItem;
		selectedItem=filechoose.showOpenDialog(null);
		exam_text.setText(selectedItem.getAbsolutePath());
		flag4=1;

    }

    @FXML
    void open_facultyfile(ActionEvent event) {
	FileChooser filechoose=new FileChooser();
		filechoose.setTitle("Select XSLX file");
		filechoose.setInitialDirectory(new File("D:\\"));
		filechoose.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("Excel files", "*.xlsx"));
	    File selectedItem;
		selectedItem=filechoose.showOpenDialog(null);
		faculty_text.setText(selectedItem.getAbsolutePath());
		flag2=1;
    
    }

    void write_to_database(String path) throws Exception
    {
      	 Class.forName("com.mysql.cj.jdbc.Driver");  
    	 Connection con=DriverManager.getConnection(  
    	 "jdbc:mysql://localhost:3306/hall","root","root");  
 
    	 try {
             FileInputStream excelFile = new FileInputStream(new File(path));
             Workbook workbook = new XSSFWorkbook(excelFile);
             Sheet datatypeSheet = workbook.getSheetAt(0);
             Iterator<Row> iterator = datatypeSheet.iterator();
             Student s[]=new Student[65];
             try{  
             while (iterator.hasNext()) {
            	 String temp[]=new String[6];
            	 int number = 0;
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
                         System.out.println(temp[i]);
                         i++;
                     }
                     if(currentCell.getCellTypeEnum()==CellType.NUMERIC)
                     {
                    	 number=(int) currentCell.getNumericCellValue();
                     }
                     }
                	 String sql="insert into students"+"(sid,tid,fid,no)"+ "values (?,?,?,?)";
                	 PreparedStatement mystmt = null;
                	 mystmt=con.prepareStatement(sql);
                	 mystmt.setString(1,temp[0]);
                	 mystmt.setString(2,temp[1]);
                	 mystmt.setString(3,temp[2]);
                	 mystmt.setInt(4,number);
                	 mystmt.execute();
             }}
                 catch(Exception e){ 
           	          e.printStackTrace();
                	 
                	 Alert a=new Alert(AlertType.CONFIRMATION);
                	 
                	 a.setContentText("Already INSERTED");
                	 a.showAndWait();
                	 Platform.exit();
                	 }  

              //   System.out.println();
             }
          catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
       	 con.close();  
         
    }
    
    @FXML
    void open_generate(ActionEvent event) {
    	System.out.println("\n clicked");
    	check_for_error();
    	if(flag1==1 && flag2==1 && flag3==1 && flag4==1)
    	{
    		Alert a=new Alert(AlertType.INFORMATION);
    		a.setContentText("Ready to Allocate file");
    		a.showAndWait();
    		String aa=student_text.getText();
    		String bb=hall_text.getText();
    		String cc=faculty_text.getText();
    		String dd=exam_text.getText();
    		try {
    			System.out.print(aa);
    		write_to_database(aa);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    			Alert ab=new Alert(AlertType.ERROR);
        		ab.setHeaderText("File Not selected");
        		ab.setContentText("Select Suitable format "+"\n 2ND YEAR---3RD YEAR---4TH YEAR");
        		ab.showAndWait();
        	
    		}
    		
    		try {
    			System.out.print(bb);
    		write_to_database1(bb);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    			Alert ab=new Alert(AlertType.ERROR);
        		ab.setHeaderText("File Not selected");
        		ab.setContentText("Select Suitable format "+"\n 2ND YEAR---3RD YEAR---4TH YEAR");
        		ab.showAndWait();
        	
    		}
    		
    		try {
    			System.out.print(cc);
    		write_to_database2(cc);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    			Alert ab=new Alert(AlertType.ERROR);
        		ab.setHeaderText("File Not selected");
        		ab.setContentText("Select Suitable format "+"\n 2ND YEAR---3RD YEAR---4TH YEAR");
        		ab.showAndWait();
        	
    		}
    		
    		try {
    			System.out.print(dd);
    		write_to_database3(dd);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    			Alert ab=new Alert(AlertType.ERROR);
        		ab.setHeaderText("File Not selected");
        		ab.setContentText("Select Suitable format "+"\n 2ND YEAR---3RD YEAR---4TH YEAR");
        		ab.showAndWait();
        	
    		}
    		
    		System.out.print("exitttt");
    		Stage stage = (Stage) generate.getScene().getWindow();
    	    stage.close();
    		
    	}
    	else
    	{
    		Alert a=new Alert(AlertType.ERROR);
    		a.setHeaderText("File Not selected");
    		a.setContentText("Select Suitable format and try again!!! Duplicate files");
    		a.showAndWait();
    	}
    }
    private void write_to_database3(String dd) {
    	 Connection con=null;
		// TODO Auto-generated method stub
    	 try {
             FileInputStream excelFile = new FileInputStream(new File(dd));
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
                         System.out.println(temp[i]);
                         i++;
                     }
                 }
                 try{  
                	 Class.forName("com.mysql.cj.jdbc.Driver");  
                	 con=DriverManager.getConnection(  
                	 "jdbc:mysql://localhost:3306/hall","root","root");  
                	 String sql="insert into examschedule"+"(cdate,cids,cidt,cidf)"+ "values (?,?,?,?)";
                	
                	 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                	 Date dt= sdf.parse(temp[0]);
                	 java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
                	 PreparedStatement mystmt = null;
                	 mystmt=con.prepareStatement(sql);
                	mystmt.setDate(1, sqlDate);
                	 mystmt.setString(2,temp[1]);
                	 mystmt.setString(3,temp[2]);
                	 mystmt.setString(4,temp[3]);
                	 mystmt.execute();
                	 }catch(Exception e){ 
                  		 
                	 }  
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
    	
    	 try {
			con.close();
			 Alert a=new Alert(AlertType.CONFIRMATION);
	    	 a.setContentText("Inserted Successfully");
	    	 a.showAndWait();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	 
	}

	private void write_to_database2(String cc) {
		// TODO Auto-generated method stub
		 try {
			 //facutly
             FileInputStream excelFile = new FileInputStream(new File(cc));
             Workbook workbook = new XSSFWorkbook(excelFile);
             Sheet datatypeSheet = workbook.getSheetAt(0);
             Iterator<Row> iterator = datatypeSheet.iterator();
             System.out.println("hello qorl");
             while (iterator.hasNext()) {
            	 String[] temp=new String[4];
                 int i=0;
                 
                 int yoe=0;
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
                     if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                    	yoe=(int) currentCell.getNumericCellValue();                     }
                 }
                 try{  
                	 Class.forName("com.mysql.cj.jdbc.Driver");  
                	 Connection con=DriverManager.getConnection(  
                	 "jdbc:mysql://localhost:3306/hall","root","root");  
                	 String sql="insert into faculty"+"(fid,name,email,yoe)"+ "values (?,?,?,?)";
                	 PreparedStatement mystmt = null;
                	 mystmt=con.prepareStatement(sql);
                	 mystmt.setString(1,temp[0]);
                	 mystmt.setString(2,temp[1]);
                	 mystmt.setString(3,temp[2]);
                	 mystmt.setInt(4, yoe);
                	 mystmt.execute();
                	 con.close();  
                	 }catch(Exception e){ 
                		 e.printStackTrace();
                		 Alert a=new Alert(AlertType.CONFIRMATION);
                	 a.setContentText("Already inserted");
                	 a.showAndWait();
                	 }  

                 System.out.println();
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	 Alert a=new Alert(AlertType.CONFIRMATION);
    	 a.setContentText("Inserted Successfully");
    	 a.showAndWait();

	}

	private void write_to_database1(String bb) throws Exception{
		// TODO Auto-generated method stub
    	 try {
             FileInputStream excelFile = new FileInputStream(new File(bb));
             Workbook workbook = new XSSFWorkbook(excelFile);
             Sheet datatypeSheet = workbook.getSheetAt(0);
             Iterator<Row> iterator = datatypeSheet.iterator();
             System.out.println("hello qorl");
             while (iterator.hasNext()) {
            	 String[] temp=new String[2];
                 int i=0;
                 int seats=0;
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
                     if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                    	 seats=(int) currentCell.getNumericCellValue();
                     }
                 }
                 try{  
                	 Class.forName("com.mysql.cj.jdbc.Driver");  
                	 Connection con=DriverManager.getConnection(  
                	 "jdbc:mysql://localhost:3306/hall","root","root");  
                	 String sql="insert into hall"+"(hid,capacity)"+ "values (?,?)";
                	 PreparedStatement mystmt = null;
                	 mystmt=con.prepareStatement(sql);
                	 mystmt.setString(1,temp[0]);
                	 mystmt.setInt(2,seats);
                	 mystmt.execute();
                	 con.close();  
                	 }catch(Exception e){ 
                		 e.printStackTrace();
                		 Alert a=new Alert(AlertType.CONFIRMATION);
                	 a.setContentText("Already inserted");
                	 a.showAndWait();
                	 }  

                 System.out.println();
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	 Alert a=new Alert(AlertType.CONFIRMATION);
    	 a.setContentText("Inserted Successfully");
    	 a.showAndWait();

	}

	void check_for_error()
    {
    	if(hall_text.getText().equals(student_text.getText()))
    	{
    		flag1=0;
    	}
    	if(hall_text.getText().contentEquals(faculty_text.getText()))
    	{
    		flag2=0;
    	}
    	if(student_text.getText().equals(faculty_text.getText()))
    	{
    		flag3=0;
    	}
    	if(student_text.getText().equals(exam_text.getText()))
    	{
    		flag4=0;
    	}
    }
    

    @FXML
    void open_hallfile(ActionEvent event) {
	   	FileChooser filechoose=new FileChooser();
		filechoose.setTitle("Select xl file");
		filechoose.setInitialDirectory(new File("D:\\"));
		filechoose.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("Excel files", "*.xlsx"));
	    File selectedItem;
		selectedItem=filechoose.showOpenDialog(null);
		hall_text.setText(selectedItem.getAbsolutePath());
		flag1=1;

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
    void open_studentfile(ActionEvent event) {
	   	FileChooser filechoose=new FileChooser();
		filechoose.setTitle("Select CSV file");
		filechoose.setInitialDirectory(new File("D:\\"));
		filechoose.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("Excel files", "*.xlsx"));
	    File selectedItem;
		selectedItem=filechoose.showOpenDialog(null);
		student_text.setText(selectedItem.getAbsolutePath());
		flag3=1;

    }
    
    

}
