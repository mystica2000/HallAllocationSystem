import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class EmailSend{			
		
		public static void main(String args[])
		{
		        final String username = "email";
		        final String password = "pass";

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
		            message.setFrom(new InternetAddress("mysticainf@gmail.com"));
		            message.setRecipients(Message.RecipientType.TO,
		                InternetAddress.parse("mysticurious00@gmail.com"));
		            message.setSubject("A testing mail header !!!");
		            message.setText("Dear Mail Crawler,"
		                + "\n\n No spam to my email, please!");

		            Transport.send(message);

		            System.out.println("Done");

		        }

		        catch (MessagingException e)
		        {
		            // throw new RuntimeException(e);
		            System.out.println(e);
		        }
		    }
}