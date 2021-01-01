package Objects;

import DAO.DAOGestor;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Clase que sirve para enviar un email al usuario en forma de alerta.
 * @author Alumno
 *
 */

public class EmailSenderService {
    /**
     * Metodo que envia el mail de alerta al correo del usuario loggeado. Para ello, hemos creado un email dentro del servicio de correo Gmail. Utiliza la libreria Javaxmail.
     * @param asunto
     * @param cuerpo
     */
    public void enviarConGMail(String asunto, String cuerpo) {

        String remitente = "radarcovidH4";   // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String clave="1234bilbao";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "miClaveDeGMail");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticacion mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(remitente));
            message.addRecipients(Message.RecipientType.TO, DAOGestor.userLogged.getEmail());   //Se podran anyadir varios de la misma manera
            message.setDescription("Radar Covid App");
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
        }
    }
}
