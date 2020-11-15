package Data;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public interface works_onDao {
    public boolean AddWorks_on(works_on works);
    public boolean updateWorks_on(works_on works);
    public List<works_on> getAllWorks();



}
