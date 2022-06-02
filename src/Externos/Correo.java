/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Externos;

import java.awt.Panel;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class Correo {

    Properties propiedad;

    String correoEnvia = "suportBijou@gmail.com";
    String contrasena = "efpzxenibgiirtur";
    String receptor;
    String asunto;
    String mensaje;
    MimeMessage mail;
    Session sesion;

    public Correo(String receptor, String asunto, String mensaje) {

        propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        propiedad.put("mail.smtp.ssl.protocols", "TLSv1.2");
        sesion = Session.getDefaultInstance(propiedad);
        
        
        
        this.receptor = receptor;
        this.asunto = asunto;
        this.mensaje = mensaje;
        mail = new MimeMessage(sesion);

    }
    
    public void enviarCorreoPDF(FileDataSource ruta, String nombreArchivo) throws MessagingException{
       
        BodyPart texto = new MimeBodyPart();
        texto.setText(mensaje);
        BodyPart adjunto = new MimeBodyPart();
        adjunto.setDataHandler(new DataHandler(ruta));
        adjunto.setFileName(nombreArchivo);       
        MimeMultipart m = new MimeMultipart();
        m.addBodyPart(texto);
        m.addBodyPart(adjunto);
        
           mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            mail.setSubject(asunto);
            mail.setContent(m);

            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia, contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transportar.close();
        
   
    }

    public void enviarEmail() {
        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            mail.setSubject(asunto);
            mail.setText(mensaje);

            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia, contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transportar.close();

        } catch (AddressException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws MessagingException {

    }

}
