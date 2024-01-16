package test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class LogicClass {
	public static void sendAttachment(String from, String to, String message, String password, String file, String subject)
			throws IOException {
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

//		Step 1 Get Session Class
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, password);
			}

		});
		session.setDebug(true);
		try {
			MimeMultipart mimeMultipart = new MimeMultipart();
			MimeBodyPart text = new MimeBodyPart();
			MimeBodyPart fileMimeBodyPart = new MimeBodyPart();
			text.setText(message);

			File file2 = new File(file);
			fileMimeBodyPart.attachFile(file2);

//			File file3 = new File("C:\\Users\\USER131\\Desktop\\Gif\\girl.jpg");
//			fileMimeBodyPart.attachFile(file3);

			mimeMultipart.addBodyPart(text);
			mimeMultipart.addBodyPart(fileMimeBodyPart);
			System.out.println("Attachment Send");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	public static void sendmail(String from, String to, String message, String password, String subject) {
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

//		Step 1 Get Session Class
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, password);
			}

		});
		session.setDebug(true);
		try {
			
			
			MimeMessage mess = new MimeMessage(session);
			mess.setFrom(from);
			mess.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mess.setSubject(subject);
			mess.setText(message);
			Transport.send(mess);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
