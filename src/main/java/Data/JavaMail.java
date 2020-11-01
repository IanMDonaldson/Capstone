package Data;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JavaMail {
    private Session session;
    private void setup() {
	
        String to = "dragomundo@outlook.com";


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
    }
}
