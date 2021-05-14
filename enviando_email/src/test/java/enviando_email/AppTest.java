package enviando_email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Unit test for simple App.
 */

public class AppTest {

	private String userName = "Colocar seu email já com permissão";
	private String senha = "Adicionar sua senha";

	@org.junit.Test
	public void testeEmail() {

		/**
		 * Ohar as configurações smtp do email e liberar o acesso (no exemplo é no
		 * gmail)
		 **/
		try {
			// Configurações do Java Mail
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true"); // autorização
			properties.put("mail.smtp.starttls", "true"); // autenticação
			properties.put("mail.smtp.host", "smtp.gmail.com"); // servidor do gmail
			properties.put("mail.smtp.port", "465"); // porta do servidor
			properties.put("mail.smtp.socketFactory.port", "465"); // porta a ser conectada pelo socket
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Classe socket de
																								// conexão

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, senha);
				}
			});
			//Destinatarios
			Address[] toUser = InternetAddress.parse("colocar os email aqui separado por virgula, exemplo - teste@gmail.com, teste2@hotmail.com");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName)); // quem está enviando
			message.setRecipients(Message.RecipientType.TO, toUser); //email de destino
			message.setSubject("Chegou o e-mail enviando com java"); //assunto
			message.setText("Olá email isso funcionou"); //corpo do email
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
