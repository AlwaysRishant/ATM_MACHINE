/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtmMachine.gui;

/**
 *
 * @author HP
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class MyAuthenticator extends Authenticator{
    private String username,password;
    public MyAuthenticator(String username,String password){
        this.username=username;
        this.password=password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        PasswordAuthentication pwdAuth=new PasswordAuthentication(this.username,this.password);        
        return pwdAuth;
    }
    
}
public class sendEmail {
    
    public static void sendMail(String sifcCode,String pin,String name,String userid) {
       String username = "goswamirishantpuri@gmail.com";
       String password = "frkcjonqssvfzkiy";
        Properties prop = new Properties();
	prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        MyAuthenticator myAuth=new MyAuthenticator(username,password);        
        Session session = Session.getInstance(prop,myAuth);                

        try {

            Message message = new MimeMessage(session);
            InternetAddress[]recipients=InternetAddress.parse(userid);
            message.setRecipients(
                    Message.RecipientType.TO,
                    recipients
            );
            if (sifcCode==null&&name==null) {
                message.setSubject("Notification From SATI BANK");
                message.setText("Your new Pin is "+pin+"\n\nnote:Don't Share it to any one");
            }
            else{
                message.setSubject("Notification From SATI BANK");
                message.setText("Detail of Account Holder from SATI BANK\n\nYour SIFC Number is "+sifcCode+"\n\nYour Pin is "+pin+"\n\nYour name in Account is "+name.toUpperCase()+"\n\nnote:Don't Share it to any one");
            }
            Transport.send(message);
         


        } catch (MessagingException e) {
            e.printStackTrace();
        }
  
    }
}
