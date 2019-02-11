package site.btsearch.core.message.event;

import org.springframework.context.ApplicationEvent;
import site.btsearch.core.tools.DateUtil;

import java.util.Date;

public abstract class MessageEvent extends ApplicationEvent{

    private Date date;

    public MessageEvent(Object source) {
        super(source);
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String getDateStr(){
        return DateUtil.getDateString(this.date,"YYYY-MM-dd");
    }



}
