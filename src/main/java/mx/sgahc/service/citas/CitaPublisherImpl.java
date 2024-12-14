package mx.sgahc.service.citas;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import mx.sgahc.model.Suscriber;
import mx.sgahc.model.citas.Cita;
import mx.sgahc.model.citas.exceptions.EmptyCitaException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class CitaPublisherImpl implements CitaPublisher{

    @Override
    public void alert(String notification, Cita cita) {
        if (cita == null || cita.getId() == null ||
                cita.getMedico() == null ||
                cita.getPaciente() == null ||
                cita.getFecha() == null) {
            throw new EmptyCitaException("Campos incompletos");
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Credenciales (deberían estar en un archivo de configuración)
        final String username = "matiasdiplojava@gmail.com";
        final String password = "rqrsldvwrmiwqigc\n";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        String paciente = cita.getPaciente().getDatosPersonales().getUsuario().getCorreoElectronico();
        String medico = cita.getMedico().getDatosPersonales().getUsuario().getCorreoElectronico();
        try {

            // Crear mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(paciente + "," + medico));

            // Asunto del correo
            message.setSubject("Notificación de Cita Médica");

            // Crear contenido HTML
            String htmlContent = String.format("""
            <html>
                <body>
                    <h2>Notificación de Cita Médica</h2>
                    <p>Estimado/a %s:</p>
                    <p>%s</p>
                    <p>Detalles de la cita:</p>
                    <ul>
                        <li>Fecha: %s</li>
                        <li>Médico: Dr./Dra. %s</li>
                    </ul>
                </body>
            </html>
            """,
                    cita.getPaciente().getDatosPersonales().getNombre(),
                    notification,
                    formatearFecha(cita.getFecha()),
                    cita.getMedico().getDatosPersonales().getNombre()
            );

            message.setContent(htmlContent, "text/html; charset=utf-8");
            message.setSentDate(new Date());

            // Enviar mensaje
            Transport.send(message);


        } catch (MessagingException e) {
            throw new EmptyCitaException("Error al enviar el correo "+e.toString());
        }

    }

    @Override
    public void unsuscribe(Cita cita) {

    }

    @Override
    public void suscribe(Cita cita, Suscriber... suscribers) {

    }

    private String formatearFecha(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(fecha);
    }
}
