package com.springStarter.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailService {

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Value("${email.imap.host}")
    private String smtpHost;

    @Value("${email.imap.port}")
    private int smtpPort;

    public void sendEmail(String to, String subject, String content) throws MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", smtpHost);
        properties.setProperty("mail.smtp.port", String.valueOf(smtpPort));
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        System.out.println(username + password);
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }
    public void readEmails() throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.setProperty("mail.imap.host", smtpHost);
        properties.setProperty("mail.imap.port", String.valueOf(smtpPort));
        properties.setProperty("mail.imap.starttls.enable", "true");

        System.out.println(username + password);
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Store store = session.getStore("imap");
        store.connect(smtpHost, username, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();
        for (Message message : messages) {
            // Process each email message
            System.out.println("Subject: " + message.getSubject());
        }

        inbox.close(false);
        store.close();
    }

}
