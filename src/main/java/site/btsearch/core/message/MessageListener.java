package site.btsearch.core.message;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import site.btsearch.core.message.event.MessageEvent;
import site.btsearch.core.message.event.WebSocketEvent;
import site.btsearch.core.websocket.WebSocketServer;

import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
* 消息监听 监听全局消息事件
* */
@Component
public class MessageListener implements ApplicationListener<MessageEvent>,Runnable{

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

    private final LinkedList linkedList = new LinkedList();

    public MessageListener(){
        scheduledExecutorService.scheduleAtFixedRate(this,5000,300, TimeUnit.MILLISECONDS);
    }

    public void onApplicationEvent(MessageEvent event) {
        if(event instanceof WebSocketEvent){
            WebSocketEvent webSocketEvent = (WebSocketEvent)event;
            WebSocketServer.SendMessage(webSocketEvent);
        }
    }

    public void run() {
        while (linkedList.size() !=0){





        }
    }
}
