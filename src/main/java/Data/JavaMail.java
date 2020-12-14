package Data;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Session;
public class JavaMail {
        Session mailSession;

        private void setMailServerProperties()
        {
            Properties emailProperties = System.getProperties();
            emailProperties.put("mail.smtp.port", "587");
            emailProperties.put("mail.smtp.auth", "true");
            emailProperties.put("mail.smtp.starttls.enable", "true");
            mailSession = Session.getDefaultInstance(emailProperties, null);
        }

        private MimeMessage draftEmailMessage(String email, String body, String subject) throws AddressException, MessagingException
        {
            String toEmail = email;
            String emailSubject = subject;
            String emailBody = body;
            MimeMessage emailMessage = new MimeMessage(mailSession);
            //Set the mail recipient
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            emailMessage.setSubject(emailSubject);
            emailMessage.setText(emailBody);// for a text email
            return emailMessage;
        }

        public void sendEmail(String email, String body, String subject) throws AddressException, MessagingException
        {
            /**
             * Sender's credentials
             * */
            setMailServerProperties();
            String fromUser = "admin@dragomundo.com";
            String fromUserEmailPassword = "NobodyKnows1234!@#$";

            String emailHost = "smtp.gmail.com";
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(emailHost, fromUser, fromUserEmailPassword);
            //Draft the message
            MimeMessage emailMessage = draftEmailMessage(email,body,subject);
            // Send the mail
            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
            transport.close();
            System.out.println("Email sent successfully.");
        }
    }
    /*private Session session;
    private void setup() throws IOException {
        String from = "admin@dragomundo.com";
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        InputStream is = JavaMail.class.getClassLoader().getResourceAsStream("app.properties");
        props.load(is);
        String password = props.getProperty("gmailpass");
        assert is != null;
        is.close();
        this.session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
    }
    public void sendMail(String toUser, String messageString, String subject) throws IOException {
	    setup();
        try {

            Message message = new MimeMessage(session);


            message.setFrom(new InternetAddress("admin@dragomundo.com"));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toUser));

            message.setSubject(subject);

            message.setText(messageString);

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }*/

