package site.btsearch.core.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JSON工具类
 * @author wangjun
 *
 */
public final class JsonUtil {

    /**
     * 工具类,不允许实例化
     */
    private JsonUtil(){}

    /***
     * @param obj 需要转化为字节数组的数据
     * @return  字节数组
     */
    public static byte[] toByteArray(Object obj){
        SerializerFeature[] SerializerFeatureS = new SerializerFeature[]{SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteMapNullValue};
        return JSONObject.toJSONBytes(obj, SerializerFeatureS);
    }

    public static JSONObject toJSONObject(String Json){
        SerializerFeature[] SerializerFeatureS = new SerializerFeature[]{SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteMapNullValue};
        return JSONObject.parseObject(Json);
    }


    public static JSONObject toJO(Object object) {
        if (object == null || object.equals("")) {
            return new JSONObject();
        }
        if (object instanceof JSONObject) {
            return (JSONObject) object;
        }
        return toJsonObject(object);
    }

    public static <T> T toJsonObject(Object object) {
        if (object instanceof String) {
            return JSON.parseObject((String) object, new TypeReference<T>() {
            });
        } else {
            return (T) JSON.toJSON(object);
        }
    }




}
