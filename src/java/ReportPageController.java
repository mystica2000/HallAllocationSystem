import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;

public class ReportPageController {

    @FXML
    private JFXButton profile;

    @FXML
    private JFXButton email;

    @FXML
    private JFXTextArea body;

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXPasswordField pass;

	private String target;

	private String a;

	@FXML
    private Text emailidhere;
    
	 
	
	
    @FXML
    public void initialize()
    {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Text Input Dialog");
    	dialog.setHeaderText("What's your favourite holiday spot?");
    	dialog.setContentText("Please enter your :");
    	
    	
    	Optional<String> result = dialog.showAndWait();
    	if(result.isPresent())
    	{
    	target = result.get();
    	}
    	try{  
    		Class.forName("com.mysql.cj.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/hall","root","root");  
    		String sql="select email from admin where name=?";
    		
    		PreparedStatement mystmt = null;
    		mystmt=con.prepareStatement(sql);
    		mystmt.setString(1,target);

    		
			ResultSet rs = mystmt.executeQuery();
			
              		  
             while(rs.next())
             {      
       	// System.out.println(rs.getString(1));
            	  a=rs.getString(1);
            	 
             }

   		con.close();  

    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    	emailidhere.setText(a);
    }
    
    @FXML
    void go_back(ActionEvent event) {

    }

    @FXML
    void open_profile(ActionEvent event) {
    	
    }

    @FXML
    void send_email(ActionEvent event) {	
    		
            final String username = a;
    		final String password = pass.getText();
    		if(password.isEmpty())
    		{
    			Alert a=new Alert(AlertType.ERROR);
    			a.setContentText("enter password of ur email");
    			a.showAndWait();
    		}
    		else {
    		Connection con=null;
	    	try{  
	    		Class.forName("com.mysql.cj.jdbc.Driver");  
	    		 con=DriverManager.getConnection(  
	    		"jdbc:mysql://localhost:3306/hall","root","root");  
	    	
	     		String sql="select ex.edate,ex.cid,ex.cname,f.name,f.email from second ex,faculty f where ex.fid=f.fid";
	    		
	    		 java.sql.Statement st=con.createStatement();
	        	 ResultSet rs = st.executeQuery(sql);
	        
	          		  
	        while(rs.next())
	        {
	        	
	        	
	        	java.sql.Date d=rs.getDate(1);
	        	String da=d.toString();
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        	Calendar c = Calendar.getInstance();
	        	try{
	        	   //Setting the date to the given date
	        	   c.setTime(sdf.parse(da));
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	 }
	        	   
	        	//Number of Days to add
	        	c.add(Calendar.DAY_OF_MONTH, 7);  
	        	//Date after adding the days to the given date
	        	String newDate = sdf.format(c.getTime());  
	        	
	        	
	        	String cid=rs.getString(2);
	        	String cname=rs.getString(3);
	        	String fname=rs.getString(4);
	        	String femail=rs.getString(5);
	        	
	        	
	        	String messages = "Dear "+fname+",\n    We Would like to notify you that the submission of the evaluation result of course "+cid+"-"+cname+" has to be done on"
	                    + " or before "+newDate+" ";
	                
	        	
	        	
	        	// System.out.println(rs.getString(1));
	        	Properties props = new Properties();
	    		props.put("mail.smtp.user","username");
	    		props.put("mail.smtp.host", "smtp.gmail.com");
	    		props.put("mail.smtp.port", "25");
	    		props.put("mail.debug", "true");
	    		props.put("mail.smtp.auth", "true");
	    		props.put("mail.smtp.starttls.enable","true");
	    		props.put("mail.smtp.EnableSSL.enable","true");

	    		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
	    		props.setProperty("mail.smtp.socketFactory.fallback", "false");  
	    		props.setProperty("mail.smtp.port", "465");  
	    		props.setProperty("mail.smtp.socketFactory.port", "465");
	    		       
	    		        Session session = Session.getInstance(props,
	    		          new javax.mail.Authenticator() {
	    		            protected PasswordAuthentication getPasswordAuthentication() {
	    		                return new PasswordAuthentication(username, password);
	    		            }
	    		          });

	    		        try {

	    		            Message message = new MimeMessage(session);
	    		            message.setFrom(new InternetAddress("l.r.darssini2000@gmail.com"));
	    		            message.setRecipients(Message.RecipientType.TO,
	    		                InternetAddress.parse("l.r.darssini2000@gmail.com"));
	    		            message.setSubject("MARK STATEMENT -REG");
	    		            message.setText(messages);

	    		            Transport.send(message);

	    		            System.out.println("Done");

	    		        }

	    		        catch (MessagingException e)
	    		        {
	    		            // throw new RuntimeException(e);
	    		            System.out.println(e);
	    		        }
	    		    }


	        
	        

	    		con.close();  
	    		}catch(Exception e)
	    	{ System.out.println(e);} 
    		}
    }
}

