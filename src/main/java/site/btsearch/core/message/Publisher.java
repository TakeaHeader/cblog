package site.btsearch.core.message;

import org.springframework.context.ApplicationEvent;

public interface Publisher {


    public void PulishEvent(ApplicationEvent event);

}
