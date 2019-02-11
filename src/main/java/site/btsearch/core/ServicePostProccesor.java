package site.btsearch.core;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import site.btsearch.core.interfaces.Service;

public class ServicePostProccesor implements BeanPostProcessor,BeanFactoryPostProcessor{

    private final Logger logger = Logger.getLogger(ServicePostProccesor.class);

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.debug("After Initiazation bean:"+beanName);
        if(bean instanceof Service){
            String ServiceName = ((Service) bean).getServiceName();


        }

        return bean;
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
