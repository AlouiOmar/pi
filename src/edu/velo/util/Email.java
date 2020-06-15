/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.util;

/**
 *
 * @author Aloui Omar
 */
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Email {
private String username = "pidevtest2020@gmail.com";
private String password = "jetestemonprojet2020";
public void envoyer(String emailDestination,String objetMessage,String contenuMessage) {
// Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        Session session = Session.getInstance(props,new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
        });
        try {
        // Etape 2 : Création de l'objet Message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("pidevtest2020@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(emailDestination));
                message.setSubject(objetMessage);
                message.setText(contenuMessage);
                // Etape 3 : Envoyer le message
                Transport.send(message);
                System.out.println("Message_envoye");
        } catch (MessagingException e) {
        throw new RuntimeException(e);
        }
}
//Etape 4 : Tester la méthode
public static void main(String[] args) {
Email test = new Email();
String txtDate=new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(new Date());
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
   LocalDateTime now = LocalDateTime.now();  
java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

   System.out.println("sql date"+date);  
//System.out.println(txtDate);
System.out.println(Double.parseDouble("dgfd"));
//test.envoyer();
} }