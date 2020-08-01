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

public class BrowseTestController {

    private String student_text;

    private String hall_text;

    private String faculty_text;

    private String exam_text;

    public int flag1,flag2,flag3,flag4;

	private int yoe;
    
    public BrowseTestController(String a,String b,String c,String d)
    {
    	student_text=a;
    	hall_text=b;
    	faculty_text=c;
    	exam_text=d;
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
                	 
                	 }  
             }
          catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
       	 con.close();  
    }
    
    void open_generate(ActionEvent event) {
    	System.out.println("\n clicked");
    	check_for_error();
    	if(flag1==1 && flag2==1 && flag3==1 && flag4==1)
    	{
    		String aa=student_text;
    		String bb=hall_text;
    		String cc=faculty_text;
    		String dd=exam_text;
    		try {
    			System.out.print(aa);
    		write_to_database(aa);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		try {
    			System.out.print(bb);
    			write_to_database1(bb);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		
    		try {
    			System.out.print(cc);
    		write_to_database2(cc);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
        	
    		}
    		
    		try {
    			System.out.print(dd);
    		write_to_database3(dd);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		
    		}
    		
    		System.out.print("exitttt");
    		
    	}
    	else
    	{
    		
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	 
	}

	private void write_to_database2(String cc) {
		 try {
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

	private String write_to_database1(String bb) throws Exception{
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
                	 }  

             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return "Inserted Successfully";
	}

	void check_for_error()
    {
    	if(hall_text.equals(student_text))
    	{
    		flag1=0;
    	}
    	if(hall_text.contentEquals(faculty_text))
    	{
    		flag2=0;
    	}
    	if(student_text.equals(faculty_text))
    	{
    		flag3=0;
    	}
    	if(student_text.equals(exam_text))
    	{
    		flag4=0;
    	}
    }

}    

	


}
