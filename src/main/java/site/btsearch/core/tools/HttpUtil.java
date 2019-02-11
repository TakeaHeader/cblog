package site.btsearch.core.tools;


import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class HttpUtil {

    private static final OkHttpClient client ;

    private static final String UserAgent = "OkHttp BtSearch V0.1";

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60000, TimeUnit.MILLISECONDS);
        builder.readTimeout(60000, TimeUnit.MILLISECONDS);
        client = builder.build();
    }

    private HttpUtil(){}

    public static JSONObject post(String url, JSONObject json){
        return Request(url,"POST",json,null);
    }

    public static JSONObject post(String url, JSONObject json, Map headers){
        return Request(url,"POST",json,headers);
    }

    public static JSONObject get(String url, JSONObject json){
        return Request(url,"GET",json,null);
    }

    public static JSONObject get(String url, JSONObject json, Map headers){
        return Request(url,"GET",json,headers);
    }

    public static String getHTML(String url, JSONObject json){
        return RequestHTML(url,"GET",json,null);
    }

    public static InputStream getInputStream(String url, JSONObject json){
        return RequestInputStream(url,"GET",json,null);
    }

    /*
    * url 地址
    * method 请求类型
    * json 请求数据
    * headers 请求头
    * */
    private static JSONObject Request(String url, String method, JSONObject json, Map headers){
        JSONObject object = null;
        RequestBody requestBody = null;
        if(json != null){
            String data = JSONObject.toJSONString(json);
            requestBody = RequestBody.create(MediaTypeEnum.JSON.getMediaType(),data);
        }
        Request.Builder builder = new Request.Builder()
                .url(url)
                .header("User-Agent",UserAgent);
        if(headers != null){
            Set<String> ketset = headers.keySet();
            Iterator<String> it = ketset.iterator();
            while (it.hasNext()){
                String key = it.next();
                builder.header(key, String.valueOf(headers.get(key)));
            }
        }
        if(requestBody != null){
            builder.method(method, requestBody);
        }
        Request request = builder.build();
        Response reponse = null;
        try{
            reponse =  client.newCall(request).execute();
        }catch (IOException e){
            // 待处理
            object = new JSONObject();
            object.put("code",-1);
            object.put("message","请求数据异常");
        }
        if(reponse != null && reponse.isSuccessful()){
            ResponseBody body = reponse.body();
            try {

                object = JSONObject.parseObject(body.string());
            }catch (IOException e){
                object = new JSONObject();
                object.put("code",-1);
                object.put("message","请求数据读取失败");
            }
        }
        return object;
    }


    private static String RequestHTML(String url, String method, JSONObject json, Map headers){
        RequestBody requestBody = null;
        if(json != null){
            String data = JSONObject.toJSONString(json);
            requestBody = RequestBody.create(MediaTypeEnum.JSON.getMediaType(),data);
        }
        Request.Builder builder = new Request.Builder()
                .url(url)
                .header("User-Agent",UserAgent);
        if(headers != null){
            Set<String> ketset = headers.keySet();
            Iterator<String> it = ketset.iterator();
            while (it.hasNext()){
                String key = it.next();
                builder.header(key, String.valueOf(headers.get(key)));
            }
        }
        if(requestBody != null){
            builder.method(method, requestBody);
        }
        Request request = builder.build();
        Response reponse = null;
        try{
            reponse =  client.newCall(request).execute();
        }catch (IOException e){
            // 待处理
            return null;
        }
        if(reponse != null && reponse.isSuccessful()){
            ResponseBody body = reponse.body();
            try {
                return  body.string();
            }catch (IOException e){
                return null;
            }
        }
        return null;
    }


    private static InputStream RequestInputStream(String url, String method, JSONObject json, Map headers){
        RequestBody requestBody = null;
        if(json != null){
            String data = JSONObject.toJSONString(json);
            requestBody = RequestBody.create(MediaTypeEnum.JSON.getMediaType(),data);
        }
        Request.Builder builder = new Request.Builder()
                .url(url)
                .header("User-Agent",UserAgent);
        if(headers != null){
            Set<String> ketset = headers.keySet();
            Iterator<String> it = ketset.iterator();
            while (it.hasNext()){
                String key = it.next();
                builder.header(key, String.valueOf(headers.get(key)));
            }
        }
        if(requestBody != null){
            builder.method(method, requestBody);
        }
        Request request = builder.build();
        Response reponse = null;
        try{
            reponse =  client.newCall(request).execute();
        }catch (IOException e){
            // 待处理
            return null;
        }
        if(reponse != null && reponse.isSuccessful()){
            ResponseBody body = reponse.body();
            try {
                return  body.byteStream();
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }


    public static void main(String[] args){
        String HTML  = getHTML("https://nyaa.si/?f=0&c=0_0&q=火影忍者",null);
        System.out.println(HTML);

    }


}
