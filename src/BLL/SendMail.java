package BLL;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.StreamProvider;
import java.util.Properties;

public class SendMail {

    public static void SendOTP(String OTP) {

        final String username = "huynhcamco42@gmail.com";
        final String password = "trxatvmaclkdvwzb";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        //prop.put("mail.smtp.auth", "true");
        //prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,null);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("huynhcamco42@gmail.com")
            );
            message.setSubject("Sending OTP");
            message.setText("Đây là mã OTP của bạn: " + OTP);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
