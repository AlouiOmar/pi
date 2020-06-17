/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_javafx.userentites;

/**
 *
 * @author Aloui Omar
 */
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import pi_javafx.userservices.ServiceUser;
 
public class Email {
        public static void sendEmail(String recepient) throws Exception{
        System.out.println("Preparing to send email");
           Properties props = new Properties();
           
           props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.starttls.enable","true");
           props.put("mail.smtp.host", "smtp.gmail.com");
           props.put("mail.smtp.port", "25" );
           
             String MyAccountEmail="ghassen4433@gmail.com"; 
             String password="ghassenesprit1996";
             
             Session session = Session.getInstance(props, new Authenticator() {
               @Override
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(MyAccountEmail, password);
               }
                 
});
             
             Message message = prepareMesssage(session,MyAccountEmail, recepient);
             Transport.send(message);
             System.out.println("Message sent sucessfully");
    }

     
    
     
    public static Message prepareMesssage(Session session, String MyAccountEmail, String recepient) {
        try {
            Connection con;
           
             Statement s=pi_javafx.utile.Connection.getInstance().getConnection().createStatement();
        
           
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MyAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My First email from Java App"+recepient);
            message.setText("Hey there \n"+recepient);        
//message.setContent(rs, recepient);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
     
       }
        return null;
    }

}