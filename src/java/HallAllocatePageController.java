import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.xdevapi.Statement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HallAllocatePageController {

    @FXML
    private JFXButton profile;

    @FXML
    private JFXButton upload_files;

    @FXML
    private JFXButton mark;

    @FXML
    private JFXButton viewfiles;

	
    List<String> aa,bb,cc;
    
    List<String> finals=new ArrayList<String>();
    
    @FXML
	public void initialize()
	{
    	viewfiles.setDisable(true);
    	mark.setDisable(true);
		 aa=new ArrayList<String>();
		 bb=new ArrayList<String>();
		 cc=new ArrayList<String>();
		 System.out.print("hell owrld");
		try{  
        	Class.forName("com.mysql.cj.jdbc.Driver");  
        	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hall","root","root");  
        	String sql="select sid,tid,fid from students";
        	java.sql.Statement st=con.createStatement();
        	 ResultSet rs = st.executeQuery(sql);  		  
        while(rs.next())
        {      
        	// System.out.println(rs.getString(1));
           aa.add(rs.getString(1));
           bb.add(rs.getString(2));
           cc.add(rs.getString(3));
        }    
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    @FXML
    void open_view(ActionEvent event) {

    	Stage stage = (Stage) viewfiles.getScene().getWindow();
        stage.close();
    	Stage primaryStage=new Stage();
    	try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("gridtry.fxml");
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
    void generate_mark(ActionEvent event) {
    	

    	Stage stage = (Stage) mark.getScene().getWindow();
        stage.close();
    	
    	Stage primaryStage=new Stage();
    	try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("browsesched.fxml");
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
    void open_browse(ActionEvent event)
    {
    	    	
    	viewfiles.setDisable(false);
    	mark.setDisable(false);
    	try {
    	int max=maxi(aa.size(),bb.size(),cc.size());
    	System.out.print(max);
    	for(int i=0;i<max;i++)
    	{
    		String temp1=aa.get(i);
    		String temp2=bb.get(i);
    		String temp3=cc.get(i);
    		if((temp1.contentEquals("1s"))&& (temp2.contentEquals("1s")))
    		{
    			List<String> a=new ArrayList<String>();
    					a=cc.subList(i, cc.size());
    			callcozone(a);
    			break;
    		}
    	    if((temp2.contentEquals("1s"))&&(temp3.contentEquals("1s")))
    		{
    			List<String> a=new ArrayList<String>();
    					a= aa.subList(i, aa.size());
    			callcozone(a);
    			break;
    		}
    		else if((temp3.contentEquals("1s"))&&(temp1.contentEquals("1s")))
    		{
    			List<String> a=new ArrayList<String>();
    			a=bb.subList(i, bb.size());
    			callcozone(a);
    			break;
    		}
    		else if(temp1.contentEquals("1s"))
			{
    			finals.add(temp2);
    			finals.add("--------");
    			finals.add(temp3);
			}
    		else if(temp2.contentEquals("1s"))
			{
    			finals.add(temp1);
    			finals.add("--------");
    			finals.add(temp3);
			}
    		else if(temp3.contentEquals("1s"))
    		{	
    			finals.add(temp1);
    			finals.add("--------");
    			finals.add(temp2);
    		}
    		else {
    			finals.add(aa.get(i));

    			finals.add(bb.get(i));
    			

    			finals.add(cc.get(i));
    		}
    	}
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.toString());
    	}
    	
    	try{  
    		int x=0;
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/hall","root","root");  
			for(int i=0;i<finals.size()-2;i++) {
			String sql="insert into allocated"+"(sid,tid,fid,x)"+ "values (?,?,?,?)";
			PreparedStatement mystmt = null;
			mystmt=con.prepareStatement(sql);
			
			mystmt.setString(1,finals.get(i));
			mystmt.setString(2, finals.get(i+1));
			mystmt.setString(3,finals.get(i+2));
			i=i+2;
			mystmt.setInt(4, x);
			
			mystmt.execute();
			x++;
			}
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
    }
    
    private void callcozone(List<String> a) {
		int size=a.size();
		int mid=size/2;
		for(int i=0;i<size/2;i++)
		{
			
			
			finals.add(a.get(i));
			finals.add("--------");
			finals.add(a.get(i+mid));
		}
	}
    
	public int maxi(int a,int b,int c)
    {
    	int max=a;
    	if(max>b && max>c)
    	{
    		return a;
    	}
    	else if(max>c && max<b)
    	{
    		max=b;
    	}
    	else
    	{
    		max=c;
    	}
    	return max;
    }
	@FXML
    void open_profile(ActionEvent event) {

		
	
	//Stage stage = (Stage) profile.getScene().getWindow();
    //stage.close();
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

}
