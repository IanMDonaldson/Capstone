package data;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail
{
    Session mailSession;

    private void setMailServerProperties()
    {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "586");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
    }

    private MimeMessage draftEmailMessage(String email) throws AddressException, MessagingException
    {
        String toEmail = email;
        String emailSubject = "Test email subject";
        String emailBody = "This is an email sent by <b>//dcia.dragomundo.com</b>.";
        MimeMessage emailMessage = new MimeMessage(mailSession);
        //Set the mail recipient
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        emailMessage.setSubject(emailSubject);
        emailMessage.setText(emailBody);// for a text email
        return emailMessage;
    }

    public void sendEmail(String email) throws AddressException, MessagingException
    {
        /**
         * Sender's credentials
         * */
        String fromUser = "dragomundoh@gmail.com";
        String fromUserEmailPassword = "Kstujdfw34hgacvmaste";

        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        //Draft the message
        MimeMessage emailMessage = draftEmailMessage(email);
        // Send the mail
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }
}
