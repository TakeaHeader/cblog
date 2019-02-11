package site.btsearch.core.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    private DateUtil(){};

    public static String getYMD(){
        return getDateString("YYYY-MM-dd");
    }

    public static String getDateString(String pattern){
        Date date = new Date();
        return getDateString(date,pattern);
    }

    public static String getDateString(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String getFull(){
        return getDateString("YYYY-MM-dd HH:mm:ss");
    }

    public static void main(String[]args){
        System.out.println(getFull());
    }
}
