package com.java.util;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

import com.java.seguridad.servlets.LogueoServlet;

public class EnvioMail {

	private static final Logger log = Logger.getLogger(LogueoServlet.class);

	public static void setEnvio(String[] receptores,String asunto,
			String contTexto,String nombreDoc,String contAdjunto) {
		Properties props = new Properties();

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port","587");
		props.setProperty("mail.smtp.user", "mshelzr@gmail.com");
		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);

		//SEGUNDA PARTE
		BodyPart texto = new MimeBodyPart();

		try {
			texto.setText(contTexto);

			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(contAdjunto)));
			adjunto.setFileName(nombreDoc);

			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);
			multiParte.addBodyPart(adjunto);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mshelzr@gmail.com"));

			int cant=receptores.length;
			InternetAddress[] arrayIA=new InternetAddress[cant];
			int i=0;

			for(String mails:receptores){
				arrayIA[i]=new InternetAddress(mails);
				i++;
			}

			message.addRecipients(Message.RecipientType.TO,arrayIA);
			message.setSubject(asunto);
			message.setContent(multiParte);

			//TERCERA PARTE
//			Transport t = session.getTransport("smtp");

//			Blockeo temporal
//			t.connect("mshelzr@gmail.com","esse6845");
//			t.sendMessage(message,message.getAllRecipients());
//			t.close();
			Logger.getLogger(EnvioMail.class).info("Se envió el correo");

		} catch (MessagingException e) {
			e.printStackTrace();
			log.error(e);
			log.error("SURGIO UN PROBLEMA AL ENVIAR");
		}
	}
}
