package company;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    public static void sendMail(String to) {

        final String from = System.getenv("MAIL_USER") != null
                ? System.getenv("MAIL_USER")
                : "shivamjain909090@gmail.com";

        final String password = System.getenv("MAIL_PASS") != null
                ? System.getenv("MAIL_PASS")
                : "nnycberuqbgxejrl";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, "User Details App"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject("Login Successful ✔");

            msg.setContent(
                "<h2>Welcome 🎉</h2>"
                + "<p>You have successfully logged in.</p>"
                + "<p><b>User Details System</b></p>",
                "text/html"
            );

            Transport.send(msg);
            System.out.println("Mail sent successfully to " + to);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
