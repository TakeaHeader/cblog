package site.btsearch.core.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import site.btsearch.core.tools.Assert;

public class SpringApplicationContextHoler implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationContextHoler.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String Name){
        Assert.isNull(applicationContext);
        return applicationContext.getBean(Name);
    }
}
