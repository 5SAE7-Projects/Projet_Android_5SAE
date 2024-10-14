package tn.espritclubs.cinquieme_SAE_sept.services;

import android.os.AsyncTask;
import android.content.Context;
import android.util.Log;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender extends AsyncTask<Void, Void, Void> {


    private static final String TAG = "EmailSender";
    private Context context;
    private String email, subject, message;

    public EmailSender(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("houssemjallouli99@gmail.com", "uujtoekvvvctgxsq"); // Use App Password
            }
        });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("houssem-eddin.jallouli@esprit.tn"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);
            Log.d(TAG, "Email sent successfully");
        } catch (MessagingException e) {
            Log.e(TAG, "Error sending email: " + e.getMessage(), e);
            if (e.getCause() != null) {
                Log.e(TAG, "Cause: " + e.getCause().getMessage());
            }
        }

        return null;
    }
}