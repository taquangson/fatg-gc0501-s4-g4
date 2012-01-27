/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name="MailController")
@RequestScoped
public class MailController {

    private String subject;
    private String body;
    /** Creates a new instance of MailController */
    public MailController() {
        subject = "";
        body = "";
    }
    public void SEND(String subject, String body) throws Exception {
// Code provide by congdongjava.com
        String smtpServer="smtp.gmail.com";
        String from = "royaleducationalacademyportal@gmail.com";
        String to = "royaleducationalacademyportal@gmail.com";
        String psw = "matkhausieumanh";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        final String login = from;
        final String pwd = psw;
        Authenticator pa = null;
        if (login != null && pwd != null) { 
            props.put("mail.smtp.auth", "true");
            pa = new Authenticator() {

                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(login, pwd);
                }
            };
        }//else: no authentication
        Session session = Session.getInstance(props, pa);
// — Create a new message –
        Message msg = new MimeMessage(session);
// — Set the FROM and TO fields –
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                to, false));

// — Set the subject and body text –
        msg.setSubject(subject);
        msg.setText(body);
// — Set some other header information –
        msg.setHeader("X-Mailer", "LOTONtechEmail");
        msg.setSentDate(new Date());
        msg.saveChanges();
// — Send the message –
        Transport.send(msg);
        FacesMessage fb = new FacesMessage("Succesful", subject + " has been sent.");  
        FacesContext.getCurrentInstance().addMessage(null, fb);  
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }
}
