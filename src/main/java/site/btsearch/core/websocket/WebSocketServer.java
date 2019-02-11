package site.btsearch.core.websocket;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import site.btsearch.core.message.event.WebSocketEvent;
import site.btsearch.core.tools.JsonUtil;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@ServerEndpoint("/websocket/{uid}")
public class WebSocketServer implements Runnable,InitializingBean{

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

    private final Logger logger = Logger.getLogger(getClass());

    private static final ConcurrentHashMap<String,Session> map = new ConcurrentHashMap();

    private static final LinkedList<WebSocketEvent> events = new LinkedList<WebSocketEvent>();

    public void afterPropertiesSet() throws Exception {
        scheduledExecutorService.scheduleAtFixedRate(this,500,500, TimeUnit.MILLISECONDS);
    }

    @OnOpen
    public void OnOpen(@PathParam("uid") String uid, Session session){
        logger.debug("新连接加入:"+uid);
        this.map.put(uid,session);
    }

    @OnClose
    public void OnClose(@PathParam("uid") String uid,Session session){
        logger.debug("连接关闭:"+uid);
        map.remove(uid);
    }

    @OnMessage
    public void OnMessage(Session session,String msg){
        logger.debug("收到消息:"+msg);
    }

    @OnError
    public void OnError(@PathParam("uid") String uid,Session session,Throwable throwable){

    }

    public static void SendGroupMessage(JSONObject msg)  throws Exception{
        Assert.notNull(msg,"msg 不能为空");
        SendGroupMessage(msg.toJSONString());
    }

    public static void SendGroupMessage(String msg)  throws Exception{
        Assert.notNull(msg,"msg 不能为空");
        Set<Map.Entry<String,Session>> sessions = map.entrySet();
        if(sessions.size() == 0){
            return;
        }
        Iterator<Map.Entry<String,Session>> nets = sessions.iterator();
        while (nets.hasNext()){
            Map.Entry<String,Session> session = nets.next();
            Session user = session.getValue();
            RemoteEndpoint.Basic basic =  user.getBasicRemote();
            basic.sendText(msg);
        }
    }

    public static void SendPtoPMessage(String uid,JSONObject msg) throws Exception{
        Assert.notNull(uid,"UID 不能为空");
        Assert.notNull(uid,"msg 不能为空");
        SendPtoPText(uid,msg.toJSONString());
    }

    public static void SendPtoPText(String uid,String msg) throws Exception{
        Assert.notNull(uid,"UID 不能为空");
        Assert.notNull(uid,"msg 不能为空");
        Session session = map.get(uid);
        if(session == null){
            throw new RuntimeException("当前操作员不在线!");
        }
        if(session.isOpen()){
            RemoteEndpoint.Basic basic =  session.getBasicRemote();
            basic.sendText(msg);
        }
    }

    public static void SendMessage(WebSocketEvent webSocketEvent){
        events.addLast(webSocketEvent);
    }

    public void run() {
        while (events.size() != 0){
            try {
                logger.debug("发现消息,开始发送消息队列!");
                WebSocketEvent webSocketEvent = events.removeFirst();
                Object object = webSocketEvent.getSource();
                if(object instanceof Map){
                    JSONObject Message = JsonUtil.toJO(object);
                    JSONObject data = Message.getJSONObject("data");
                    int type = Message.getIntValue("Type");
                    if(type == MessageType.MSG_PTP){
                        String uid = Message.getString("uid");
                        if(uid != null){
                            SendPtoPMessage(uid,data);
                        }else {
                            logger.error("uid not found");
                        }
                    }else if(type == MessageType.MSG_ALL){
                        SendGroupMessage(data);
                    }else{
                        logger.debug("消息类型未指定");
                    }
                }
            }catch (Exception e){
                logger.error(e);
            }
        }
    }


}
