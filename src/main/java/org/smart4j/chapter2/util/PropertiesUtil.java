package org.smart4j.chapter2.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件工具类
 * Created by admin on 2017/5/6.
 */
public class PropertiesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 加载属性文件
     */
    public static Properties loadProps(String fileName){
        Properties properties = null;
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(inputStream == null){
                throw new FileNotFoundException(fileName+"file is not found");
            }
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("load properties file failure",e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure",e);
                }
            }
        }
        return properties;
    }

    /**
     * 获取字符串属性（默认为空字符串）
     */
    public static String getString(Properties props,String key){
        return getString(props,key,"");
    }

    /**
     *  获取字符串属性（可指定默认值）
     */
    public static String getString(Properties props,String key,String defaultValue){
        String value = defaultValue;
        if (props.containsKey(key)){
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     *  获取数值型属性（默认值为0）
     */
    public static int getInt(Properties props,String key){
        return getInt(props,key,0);
    }

    /**
     * 获取数值型属性（可指定默认值）
     */
    public static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if(props.containsKey(key)){
            value = CastUtil.castInt(props.getProperty(key));
        }
        return  value;
    }

    /**
     *  获取布尔型属性值 （默认为false）
     */
    public static boolean getBoolean(Properties props,String key){
        return getBoolean(props,key,false);
    }

    /**
     *  获取布尔型属性值 （可指定默认值）
     */
    public static boolean getBoolean(Properties props,String key,boolean defaultValue){
        boolean value = defaultValue;
        if (props.containsKey(key)){
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }

}
