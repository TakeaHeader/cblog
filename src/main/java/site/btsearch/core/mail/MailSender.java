package site.btsearch.core.mail;

public interface MailSender {


    public void SendEmail(String to,String subject,String Content) throws Exception;


}
