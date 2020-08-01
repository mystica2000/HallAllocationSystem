import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ViewPageController {

    @FXML
    private TableView<Students> table;

    @FXML
    private TableColumn<Students, String> stud1;

    @FXML
    private TableColumn<Students,String> stud2;

    @FXML
    private TableColumn<Students,String> stud3;

    @FXML
    private ChoiceBox<String> hall;

    @FXML
    private ChoiceBox<String> exam;

    @FXML
    private Text faculty;

    @FXML
    private Text exam1;

    @FXML
    private Text exam3;

    @FXML
    private Text exam2;

    @FXML
    private JFXButton create;

	private List<String> allocate;

	ArrayList<String> hallno;
	ArrayList<String> hallsize;
	
	
	ArrayList<String> aa,bb,cc,dd;

	private ArrayList<String> fa;

	private ArrayList<String> fb;

	private ArrayList<String> fc;

	private ArrayList<Integer> fd;
	
	private final ObservableList<Students> data =
            FXCollections.observableArrayList(
            new Students("Jacob", "Smith", "jacob.smith@example.com"),
            new Students("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Students("Ethan", "Williams", "ethan.williams@example.com"),
            new Students("Emma", "Jones", "emma.jones@example.com"),
            new Students("Michael", "Brown", "michael.brown@example.com"));
	
    @FXML
    public void initialize()
    {
    	table=new TableView<Students>();
    	
    	stud1.setCellValueFactory(new PropertyValueFactory<Students, String>("second"));
    	stud2.setCellValueFactory(new PropertyValueFactory<Students, String>("third"));
    	stud3.setCellValueFactory(new PropertyValueFactory<Students, String>("four"));

    	
    	table.setItems(this.data);
    	table.getColumns().add(stud1);

    	table.getColumns().add(stud2);
    	table.getColumns().add(stud3);
    	
    	/*hallno=new ArrayList<String>();
    	hallsize=new ArrayList<String>();
    	
    	Connection con=null;
    	try{  
    		Class.forName("com.mysql.cj.jdbc.Driver");  
    		 con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/hall","root","root");  
    		String sql="select hid,capacity from hall";
        	java.sql.Statement st=con.createStatement();
        	 ResultSet rs = st.executeQuery(sql);
        
               		  
        while(rs.next())
        {      
        	// System.out.println(rs.getString(1));
          hallno.add(rs.getString(1));
          hallsize.add(rs.getString(2));  
        }

    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    	
    	aa=new ArrayList<String>();
    	bb=new ArrayList<String>();
    	cc=new ArrayList<String>();
    	dd=new ArrayList<String>();
    	
    	
    	try{  
    		Class.forName("com.mysql.cj.jdbc.Driver");  
    		 con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/hall","root","root");  
    		String sql="select * from examschedule";
        	java.sql.Statement st=con.createStatement();
        	 ResultSet rs = st.executeQuery(sql);
        
               		  
        while(rs.next())
        {      
        	aa.add(rs.getDate(1).toString());
        	bb.add(rs.getString(2));
        	cc.add(rs.getString(3));
        	dd.add(rs.getString(3));
        }
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    	
    	fa=new ArrayList<String>();
    	fb=new ArrayList<String>();
    	fc=new ArrayList<String>();
    	fd=new ArrayList<Integer>();
    	try{  
    		Class.forName("com.mysql.cj.jdbc.Driver");  
    		 con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/hall","root","root");  
    		String sql="select * from faculty order by yoe";
        	java.sql.Statement st=con.createStatement();
        	 ResultSet rs = st.executeQuery(sql);
        
               		  
        while(rs.next())
        {      
        	fa.add(rs.getString(1));
        	fb.add(rs.getString(2));
        	fc.add(rs.getString(3));
        	fd.add(rs.getInt(3));
        }
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    	
    	ObservableList<String> no= FXCollections.observableArrayList(hallno);
    	
    	
    	
    	hall.setItems(no);
		hall.setValue("examvalue");
		
		ObservableList<String> date= FXCollections.observableArrayList(aa);
		exam.setItems(date);
		exam.setValue("datevalue");
		
		
		/*table=new TableView<Student>();
		
		stud1=new TableColumn<>("student1");
	    stud1.setCellValueFactory(new PropertyValueFactory<>("sid"));
				
		stud2=new TableColumn<>("student2");
	    stud2.setCellValueFactory(new PropertyValueFactory<>("tid"));
		
		stud3=new TableColumn<>("student3");
	    stud3.setCellValueFactory(new PropertyValueFactory<>("fid"));
	    
	    table.getColumns().add(stud1);
	    table.getColumns().add(stud2);
	    table.getColumns().add(stud3);
	    
	   */
		
	    
		/*String choices1=hall.getValue();
		String choices2=exam.getValue();
		Fill_table(choices1,choices2);*/
		 /*table.getItems().add(new Student("17bit006","16bit005","17bit343"));
	 	    table.getItems().add( new Student("17bit006","17bit006","17nit001"));
	 	    */
		
		    }
    
    
    
    private void Fill_table(String choices1, String choices2) {
		// TODO Auto-generated method stub
    
	}




	@FXML
    void create_pdf(ActionEvent event) {

    }



	public void passiton(List<String> finals) {
		// TODO Auto-generated method stub
		allocate=new ArrayList<String>();
		allocate=finals;
	}
	
	  public static class Students {
		  
	        private final SimpleStringProperty second;
	        private final SimpleStringProperty third;
	        private final SimpleStringProperty four;
	 
	        private Students(String second, String third, String four) {
	            this.second = new SimpleStringProperty(second);
	            this.third = new SimpleStringProperty(third);
	            this.four = new SimpleStringProperty(four);
	        }
	 
	        public String getSecondName() {
	            return second.get();
	        }
	 
	        public void setSecondName(String fName) {
	            second.set(fName);
	        }
	 
	        public String getThirdName() {
	            return third.get();
	        }
	 
	        public void setThirdName(String fName) {
	            third.set(fName);
	        }
	 
	        public String getFourName() {
	            return four.get();
	        }
	 
	        public void setFourName(String fName) {
	            four.set(fName);
	        }
	    }
	} 

