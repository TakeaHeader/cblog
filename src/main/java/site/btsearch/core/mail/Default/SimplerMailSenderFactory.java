package site.btsearch.core.mail.Default;

import site.btsearch.core.mail.MailSender;
import site.btsearch.core.mail.MailSenderFactory;
import org.apache.log4j.Logger;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

public class SimplerMailSenderFactory implements MailSenderFactory,InvocationHandler {

    private final Logger logger = Logger.getLogger(getClass());

    private SimplerMailSender simplerMailSender;

    public MailSender newMailSenderInstance(String protocol,String host,String email,String username,String Password) {
        if(protocol == null || protocol.length() == 0){
            protocol = "smtp";
        }
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", protocol);
        props.setProperty("mail.smtp.host", host);
        this.simplerMailSender = new SimplerMailSender(props,email,username,Password);
        return warpMailSender(this.simplerMailSender);
    }

    public MailSender newMailSenderInstance(String host,String email,String username,String Password) {
        return newMailSenderInstance(null,host,email,username,Password);
    }

    private MailSender warpMailSender(MailSender mailSender){
        ClassLoader classLoader = mailSender.getClass().getClassLoader();
        Class [] classes = new Class[]{MailSender.class};
        return (MailSender)Proxy.newProxyInstance(classLoader,classes,this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.debug(proxy.getClass());
        logger.debug(method.getName());
        logger.debug(args);
        return method.invoke(simplerMailSender,args);
    }

    public static void main(String [] args) throws Exception{
        MailSenderFactory mailSenderFactory = new SimplerMailSenderFactory();
        MailSender mailSender = mailSenderFactory.newMailSenderInstance("smtp.qq.com","1142482404@qq.com","1142482404@qq.com","rhhlpglxztukjjgh");
        mailSender.SendEmail("wangjun@jl-soft.com","你好","测试邮件");
        System.out.println("你好,发送成功");
    }
}
