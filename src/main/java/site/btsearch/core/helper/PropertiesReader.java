package site.btsearch.core.helper;


import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static final Logger logger = Logger.getLogger(PropertiesReader.class) ;

    private final String PATH = System.getProperty("user.dir") + System.getProperty("file.separator");

    private static final Properties PROP = new Properties();

    private static PropertiesReader reader = null;

    private PropertiesReader(String subPath) throws IOException{
        PROP.load(new FileInputStream(PATH+subPath));
    }

    public static PropertiesReader NewInstance(String PATH){
            if(reader == null){
                try{
                    reader = new PropertiesReader(PATH);
                }catch (Exception e){
                    logger.error("Properties file is not exists!");
                }
            }
            return reader;
    }

    public static PropertiesReader NewInstance(){
        return NewInstance("config.prop");
    }

    public String getProperty(String key){
        return PROP.getProperty(key);
    }

}
