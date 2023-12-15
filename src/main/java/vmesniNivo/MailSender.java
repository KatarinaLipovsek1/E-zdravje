package vmesniNivo;

import java.io.Serializable;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.naming.InitialContext;
import javax.naming.NamingException;


public class MailSender implements Serializable {
    InitialContext ic;
    static Session s;

    {
        try {
            ic = new InitialContext();
            String snName = "java:jboss/mail/hMailServer";
            s = (Session)ic.lookup(snName);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }


    public static void send(String to, String from, String subject, String body) throws Exception {
        Message message = new MimeMessage(s);
        message.setFrom(new InternetAddress(from));
        Address toAddress = new InternetAddress(to);
        message.addRecipient(Message.RecipientType.TO, toAddress);
        message.setSubject(subject);
        message.setContent(body, "text/plain");
        Transport.send(message);
    }


}