import java.sql.*;
public class Connect{  
public static void main(String args[]){  
try{  
Class.forName("com.mysql.cj.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/hall","root","root");  
String sql="insert into admin"+"(fid,pass,name,email,dept,contact)"+ "values (?,?,?,?,?,?)";
PreparedStatement mystmt = null;
mystmt=con.prepareStatement(sql);
mystmt.setInt(1,101);
mystmt.setString(2,"mystica");
mystmt.setString(3, "curious123");
mystmt.setString(4,"mysticainf@gmail.com");
mystmt.setString(5, "IT");
mystmt.setInt(6, 987);
mystmt.execute();
con.close();  
}catch(Exception e){ System.out.println(e);}  
}  
}  