package test;

import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSending {
	public static void main(String[] args) throws IOException {
//		String receiver="neeluchandra43@gmail.com";
		System.out.println("hello");
		final String username = "neeluchandra60@gmail.com";
		final String password = "preasvotezbymxji";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try

		{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("neeluchandra60@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("neeluchandra60@gmail.com, aanchal.c@projectontrack.net"));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress("sanjaya.s@projectontrack.net"));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress("neelu.c@projectontrack.net"));
//			mess.addRecipient(Message.RecipientType.BCC, new InternetAddress("saicharan.a@projectontrack.net"));
			message.setSubject("This is a Testing Mail");
			BodyPart messageBody = new MimeBodyPart();
			messageBody.setText("Dear Mail Crawler," + "\n\n I have Sent you an attachment please find below !");

			MimeBodyPart messageBody2 = new MimeBodyPart();
			String filename = "D:\\Desktop\\Gif\\girl.jpg";
			DataSource source = new FileDataSource(filename);
			messageBody2.setDataHandler(new DataHandler(source));
			messageBody2.setFileName(filename);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBody);
			multipart.addBodyPart(messageBody2);

			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Message Sent");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
