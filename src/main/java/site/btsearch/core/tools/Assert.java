package site.btsearch.core.tools;

/**
 * @Author  wangjun
 * @description 常用判断工具集
 * @Date  2018/4/4
 *
 */
public class Assert {

    private Assert (){}

    /***
     * 判断是否为null
     * @param object 参数
     */
    public static void isNull(Object object){
        if(object == null){
            throw new NullPointerException();
        }
    }
}
