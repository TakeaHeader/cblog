package site.btsearch.core.mail.Default;

import site.btsearch.core.mail.MailSender;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SimplerMailSender implements MailSender {

    private final Logger logger = Logger.getLogger(getClass());

    private String FromEmail;

    private Properties properties;

    private String userName;

    private String password;

    private Session session;

    public SimplerMailSender(Properties properties,String email,String userName,String password){
        this.properties = properties;
        this.session = Session.getInstance(properties);
        this.session.setDebug(true);
        this.userName = userName;
        this.password = password;
        this.FromEmail = email;
    }


    public void SendEmail(String to,String subject,String Content) throws Exception{
        MimeMessage message = createMessage(this.session,to,subject,Content);
        Transport transport = null;
        try {
            transport =  this.session.getTransport();
            transport.connect(userName,password);
            transport.sendMessage(message,new InternetAddress[]{new InternetAddress(to)});
        }catch (Exception e){
            logger.error("错误:邮件发送失败",e);
            throw  e;
        }
        if(transport != null){
            try {
                transport.close();
            }catch (Exception e){
                logger.error("错误:邮件服务器连接关闭失败",e);
                throw  e;
            }
        }
    }

    private MimeMessage createMessage(Session session,String to,String subject,String Content){
        return createMessage(session,FromEmail,to,subject,Content);
    }

    private MimeMessage createMessage(Session session,String from,String to,String subject,String Content){
        MimeMessage mimeMessage = new MimeMessage(session);
        try{
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setRecipient(Message.RecipientType.CC,new InternetAddress(to));
            mimeMessage.setSubject(subject,"UTF-8");
            mimeMessage.setContent(Content, "text/html;charset=UTF-8");
            mimeMessage.setSentDate(new Date());
        }catch (Exception e){
            if(logger.isDebugEnabled()){
                logger.error("错误:邮件地址有误",e);
            }
            return  null;
        }

        return mimeMessage;
    }
}
