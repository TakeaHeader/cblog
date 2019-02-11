package site.btsearch.core.mail;

public interface MailSenderFactory {

    public MailSender newMailSenderInstance(String host,String email,String username,String Password) throws Exception;

    public MailSender newMailSenderInstance(String protocol,String host,String email,String username,String Password) throws Exception;



}
