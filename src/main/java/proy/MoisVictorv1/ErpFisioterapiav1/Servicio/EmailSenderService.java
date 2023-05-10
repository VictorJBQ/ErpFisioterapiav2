package proy.MoisVictorv1.ErpFisioterapiav1.Servicio;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;

	public EmailSenderService(JavaMailSender emailSender) {
		this.mailSender = emailSender;
	}

	public void sendSimpleEmail(String toEmail, String token, String user) throws IOException {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("jimbussolutions@gmail.com");
			helper.setTo(toEmail);
			helper.setSubject("Restablecimiento de contraseña");

			String htmlBody = "<!DOCTYPE html>\n" + "<html xmlns:th=\"http://www.thymeleaf.org\" lang=\"es\">\n" + "\n"
					+ "  <head>\n" + "    <title>Restablecimiento de contraseña</title>\n" + "    <style>\n"
					+ "      /* Estilos generales */\n" + "      body {\n" + "        background-color: #f8f8f8;\n"
					+ "        font-family: Arial, sans-serif;\n" + "        font-size: 16px;\n"
					+ "        line-height: 1.4;\n" + "        margin: 0;\n" + "        padding: 0;\n" + "      }\n"
					+ "      \n" + "      /* Estilos para el cuadrado central */\n" + "      #container {\n"
					+ "        background-color: #fff;\n" + "        border: 1px solid #ccc;\n"
					+ "        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" + "        margin: 50px auto;\n"
					+ "        max-width: 500px;\n" + "        padding: 20px;\n" + "        text-align: center;\n"
					+ "      }\n" + "      \n" + "      #container img {\n" + "        max-width: 100%;\n"
					+ "        height: auto;\n" + "        margin-right: 20px;\n" + "      }\n" + "    </style>\n"
					+ "  </head>\n" + "  <body style=\"background-color: #f8f8f8;\">\n" + "    <div id=\"container\">\n"
					+ "      <img src=\"https://img.freepik.com/foto-gratis/concepto-rpa-pantalla-tactil-mano-borrosa_23-2149311914.jpg?w=1060&t=st=1683586515~exp=1683587115~hmac=74407302ad528d9aa6ddd6061b4951fb45d6d865152c59cd25736e208b1a630d\">\n"
					+ "      <h1>Restablecimiento de contraseña</h1>\n" + "     <h1>Hola " + user + "</h1>\n"
					+ "      <p>Hemos recibido una solicitud de restablecimiento de contraseña de tu cuenta.</p>\n"
					+ "      <p>Haz clic en el botón que aparece a continuación para cambiar tu contraseña.</p>\n"
					+ "      <p>Ten en cuenta que este enlace es válido solo durante 5 minutos. Una vez transcurrido el plazo, deberás volver a solicitar el restablecimiento de la contraseña.</p>\n"
					+ "     <a href=\""+token+"\" style=\"background-color: #4CAF50; border: none; color: white; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin-top: 20px;\">Restablecer contraseña</a>\n"
					+ "    </div>\n" + "  </body>\n" + "</html>";

			helper.setText(htmlBody, true);
			mailSender.send(message);
			System.out.println("Mail Send...");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
