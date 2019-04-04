package main.com.store.utils;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class MailUtils {
    private static String fromUserName;
    private static String fromPassword;
    private static String subject;
    private static String content;
    private static String host;
    private static String auth;

    static {
        Properties properties = new Properties();
        try {
            InputStream inputStream = MailUtils.class.getClassLoader().getResourceAsStream("resources/mail.yml");
            //注意这里不能这样使用了,因为普通java程序和web应用程序的相对路径不同使用资源加载器是正确的使用方法
            //InputStream inputStream=new FileInputStream("/resources/mail.yml");
            properties.load(inputStream);
            fromUserName = properties.getProperty("username", "admin");
            fromPassword = properties.getProperty("password", "admin");
            subject = new String(properties.getProperty("subject", "").getBytes("iso-8859-1"), "utf-8");
            content = new String(properties.getProperty("content", "").getBytes("iso-8859-1"), "utf-8");
            host = properties.getProperty("host", "localhost");
            auth = properties.getProperty("auth", "true");
            //System.out.println(fromUserName + " " + fromPassword + " " + subject + " " + content + " " + host + " " + auth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendSimpleMail(String to,String activeCode) {
        try {
            Properties properties = new Properties();
            properties.setProperty("mail.host", host);
            properties.setProperty("mail.smtp.auth", auth);
            Authenticator passwordAuthentication = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromUserName, fromPassword);
                }
            };
            Session session = Session.getDefaultInstance(properties, passwordAuthentication);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromUserName));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content.replaceAll("@",activeCode), "text/html;charset=utf-8");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //MailUtils.sendSimpleMail("fwk-test@store.com");
        System.out.println("ok" );
    }

}
