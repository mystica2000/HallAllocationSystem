import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.jfoenix.controls.JFXButton;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class gridcontroller {

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

    @FXML
    private Text woah;

    @FXML
    private JFXButton over;


	
	
    
    
    @FXML
    void overfinish(ActionEvent event) {
    	try {
	        /*Loading the fxml file for the first page*/
	    		URL url = getClass().getResource("hall.fxml");
	            if (url == null) {
	                System.out.println("Can't load FXML file");
	                Platform.exit();
	            }
	            System.out.print("heeyy");
	    		Stage primaryStage=new Stage();
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
    private JFXButton pdfformat;
    
    public List<String> allocate;

	ArrayList<String> hallno;
	ArrayList<String> hallsize;
	
	
	ArrayList<String> aa,bb,cc,dd;

	private ArrayList<String> fa;

	private ArrayList<String> fb;

	private ArrayList<String> fc;

	private ArrayList<Integer> fd;


	private int p;

	private List<String> temp;

	private int w;

	private int track;

	@FXML
    private Text emailidhere;
    
	 
	PdfDocument pdfDoc;
	
	Document document;

	private int w1;

	private int q1;
	
	
	  @FXML
	    public void initialize()
	    {
		  
			
		  	
		  
	    	hallno=new ArrayList<String>();
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
	        	dd.add(rs.getString(4));
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
	        	
	        }
	    		con.close();  
	    		}catch(Exception e){ System.out.println(e);}  
	    	
	    	ObservableList<String> no= FXCollections.observableArrayList(hallno);
	    	
	    	hall.setItems( no);
			hall.setValue("examvalue");
			
			ObservableList<String> date= FXCollections.observableArrayList(aa);
			exam.setItems(date);
			exam.setValue("datevalue");	
	    }
	
    @FXML
    void create_pdf(ActionEvent event) {
    
    	String a=hall.getValue();
    	System.out.println(a);
    	String b=exam.getValue();
    	for(w=0;w<aa.size();w++)
    	{
    		if(b.contentEquals(aa.get(w)))
    		{
    			exam1.setText(bb.get(w));
    			exam2.setText(cc.get(w));
    			exam3.setText(dd.get(w));
    			break;
    		}
    	}
    	for(int i=0;i<hallno.size();i++)
    	{
    		if(a.contentEquals(hallno.get(i)))
    		{
    			callem(i);
    			break;
    		}
    	}
    	
    }


    ArrayList<String> removeDuplicates(ArrayList<String> tree) 
    { 
  
        // Create a new ArrayList 
        ArrayList<String> newList = new ArrayList<String>(); 
  
        // Traverse through the first list 
        for (String element : tree) { 
  
            // If this element is not present in newList 
            // then add it 
            if (!newList.contains(element)) { 
  
                newList.add(element); 
            } 
        } 
  
        // return the new list 
        return newList; 
    } 
    
    
	private void callem(int i) {
		// TODO Auto-generated method stub
		allocate=new ArrayList<String>();
		String foot="";
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/hall","root","root");  
		
			String sql="select * from allocated order by x;";
			java.sql.Statement st=con.createStatement();
       	 ResultSet rs = st.executeQuery(sql);
       
              		  
       while(rs.next())
       {      
       	// System.out.println(rs.getString(1));
        allocate.add(rs.getString(1));
        allocate.add(rs.getString(2)); 
        allocate.add(rs.getString(3));
		}
       temp=new ArrayList<String>();
        temp=allocate;
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
		
		ArrayList<String> tree=new ArrayList<String>();
		for(int q=0;q<allocate.size();q++)
		{
			char a=allocate.get(q).charAt(1);
			String v=""+a;
			tree.add(v);
		}
		Collections.sort(tree);
		ArrayList<String> qwert=
				new ArrayList<String>();
		qwert=removeDuplicates(tree);
		
			if(bb.get(w).equals("NULLS"))
		     {
				String com1=qwert.get(0);
		      temp.removeIf(n -> (n.charAt(1)==com1.charAt(0)));
		     }

			if(cc.get(w).equals("NULLS"))
		     {
				String com1=qwert.get(2);
			    
				temp.removeIf(n -> (n.charAt(1)==com1.charAt(0)));
		     }

			if(dd.get(w).equals("NULLS"))
		     {
				
				System.out.print("one step");
				String com1=qwert.get(1);
			    
		      temp.removeIf(n -> (n.charAt(1)==com1.charAt(0)));
		     }
		
		
		int y=i+1;
		int count=0;
		int m=0;
		if(i==0)
		{
		track=i*Integer.parseInt(hallsize.get(i));
	    m=y*Integer.parseInt(hallsize.get(i));
		
		}
		else
		{
			int trac=0;
			//ArrayList<Integer> al=new ArrayList<Integer>();
			for(int i1=0;i1<hallsize.size();i1++)
			{
				if(i==i1)
				{
					track=trac;
					break;
				}
				trac=trac+Integer.parseInt(hallsize.get(i1));
		
			}
			m=trac+Integer.parseInt(hallsize.get(i));
		}
		//track=track+i*Integer.parseInt(hallsize.get(i));
		for(int j=track;j<m;j++)
		{
			if(j>=temp.size())
			{
			  break;	
			}
			
			if(count==3)
			{
				foot=foot+"\n";
				count=0;
			}
			int o=j+1;
			
			if(temp.get(j).contentEquals("--------"))
			{
				String a=temp.get(i);
				a="                 ";
				foot=foot+a;
			}
			else
			{
				foot=foot+temp.get(j);
			}
			//temp.remove(j);
			foot+="  ";
			count++;
		}
		System.out.println(foot);
		woah.setText(foot);
		if(p==fb.size())
		{
			p=0;
			Collections.shuffle(fb);
		}
		
		faculty.setText(fb.get(p));
		
		if(foot.isEmpty())
		{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/hall","root","root");  
			String sql="insert into hallexam"+"(hid,edate,fid)"+ "values (?,?,?)";
			PreparedStatement mystmt = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
         	 Date dt= sdf.parse(aa.get(i));
       	 java.sql.Date sqlDate = new java.sql.Date(dt.getTime());

			mystmt=con.prepareStatement(sql);
			mystmt.setString(1,hall.getValue());
			mystmt.setString(3,fb.get(p));
			mystmt.setDate(2,sqlDate );
			mystmt.execute();

			con.close();  
			}catch(Exception e){ System.out.println(e);}  
		}
		
		p++;
		
	}
	
	@FXML
    void pdf_writer(ActionEvent event) {
		String dest = "shed.pdf"; 
		PdfWriter writer = null;
		try {
			writer = new PdfWriter(dest);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PdfDocument pdfDoc = new PdfDocument(writer);
		pdfDoc.addNewPage(); 
		Document document = new Document(pdfDoc); 
		
		int flag=0;
		
		for(q1=0;q1<aa.size();q1++)
		{
			for(w1=0;w1<hallno.size();w1++)
			{
				ArrayList<String> how=new ArrayList<String>();
				how=callem1(w1);
				    if(flag==2)
				    {
				    	document.add(new AreaBreak());
				    	flag=0;
				    }
					if(!how.isEmpty())
					{
						document.add(new Paragraph("DATE: "+aa.get(q1)+"  \t   HALLNO: "+hallno.get(w1)));
						float [] pointColumnWidths = {150F, 150F, 150F}; 
						Table table = new Table(pointColumnWidths); 
						flag++;
					for(int p=0;p<how.size();p++)
					{
						table.addCell(how.get(p));
					}
					table.setVerticalBorderSpacing(10);
				    table.setHorizontalBorderSpacing(10);
					document.add(table);
					}
		    }
		}
		document.close();
		
				
    }

	private ArrayList<String> callem1(int i) {
		
		ArrayList<String> prints=new ArrayList<String>();
		
		
		// TODO Auto-generated method stub
		allocate=new ArrayList<String>();
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/hall","root","root");  
		
			String sql="select * from allocated order by x;";
			java.sql.Statement st=con.createStatement();
       	 ResultSet rs = st.executeQuery(sql);
       
              		  
       while(rs.next())
       {      
       	// System.out.println(rs.getString(1));
        allocate.add(rs.getString(1));
        allocate.add(rs.getString(2)); 
        allocate.add(rs.getString(3));
		}
       temp=new ArrayList<String>();
        temp=allocate;
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
		
		ArrayList<String> tree=new ArrayList<String>();
		for(int q=0;q<allocate.size();q++)
		{
			char a=allocate.get(q).charAt(1);
			String v=""+a;
			tree.add(v);
		}
		Collections.sort(tree);
		ArrayList<String> qwert=
				new ArrayList<String>();
		qwert=removeDuplicates(tree);
		
			if(bb.get(q1).equals("NULLS"))
		     {
				String com1=qwert.get(0);
		      temp.removeIf(n -> (n.charAt(1)==com1.charAt(0)));
		     }

			if(cc.get(q1).equals("NULLS"))
		     {
				String com1=qwert.get(2);
			    
				temp.removeIf(n -> (n.charAt(1)==com1.charAt(0)));
		     }

			if(dd.get(q1).equals("NULLS"))
		     {
				
				System.out.print("one step");
				String com1=qwert.get(1);
			    
		      temp.removeIf(n -> (n.charAt(1)==com1.charAt(0)));
		     }
		
			String printwriter; 
		int y=i+1;
		int count=0;
		int m=0;
		if(i==0)
		{
		track=i*Integer.parseInt(hallsize.get(i));
	    m=y*Integer.parseInt(hallsize.get(i));	
		}
		else
		{
			int trac=0;
			//ArrayList<Integer> al=new ArrayList<Integer>();
			for(int i1=0;i1<hallsize.size();i1++)
			{
				if(i==i1)
				{
					track=trac;
					break;
				}
				trac=trac+Integer.parseInt(hallsize.get(i1));
			}
			m=trac+Integer.parseInt(hallsize.get(i));
		}
		//track=track+i*Integer.parseInt(hallsize.get(i));
		for(int j=track;j<m;j++)
		{
			if(j>=temp.size())
			{
				
			  break;	
			}
			
			if(count==3)
			{
				count=0;
			}
			
			if(temp.get(j).contentEquals("--------"))
			{
				String a=temp.get(i);
				a="                 ";
				printwriter=a;
			}
			else
			{
			printwriter = temp.get(j);
			
			}
			prints.add(printwriter);   	     
			count++;
		}
		
		if(p==fb.size())
		{
			p=0;
			Collections.shuffle(fb);
		}
		  return prints;
		/*try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/hall","root","root");  
			String sql="insert into hallexam"+"(hid,edate,fid)"+ "values (?,?,?)";
			PreparedStatement mystmt = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
         	 Date dt= sdf.parse(aa.get(i));
       	 java.sql.Date sqlDate = new java.sql.Date(dt.getTime());

			mystmt=con.prepareStatement(sql);
			mystmt.setString(1,hallno.get(i));
			mystmt.setString(3,fb.get(p));
			mystmt.setDate(2,sqlDate );
			mystmt.execute();

			con.close();  
			}catch(Exception e){ System.out.println(e);}  */
		
	}

private static Cell getCell(String cm) {
    Cell cell = new Cell();
      Paragraph p = new Paragraph(cm);
      p.setTextAlignment(TextAlignment.CENTER);
    cell.add(p);
      return cell;
}
}
