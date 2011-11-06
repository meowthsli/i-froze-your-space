package ru.meowth.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import java.io.IOException;
import java.util.Properties;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ru.meowth.domain.Letter;


public class MailSenderService {
	
	private Session session;
	
	/**
	 * Ctor
	 */
	public MailSenderService() {
		Properties props = new Properties();
		session = Session.getDefaultInstance(props, null);
	}
	
	/**
	 * Sends letter
	 * @param letter
	 * @throws IOException
	 */
	public void sendMail(Letter letter) throws IOException {
		
        try {
            Message msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("i.froze.your.space@gmail.com"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(letter.getEmail(), letter.getEmail()));
            msg.setSubject("Your frozen space is ready");
            msg.setText(letter.getBody());
            
            Transport.send(msg);
    
        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        } catch (IOException e) {
            // ...
        }
	}

}
