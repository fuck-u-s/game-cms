package com.cms.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法描述:处理返回类型
 * 主要统一返回接口类型
 * @author 小刘
 * @version v1.0
 * @date 2015/10/30
 */
public class ResultUtil {

    public static Map<String,String> map(){
        return new HashMap<String,String>();
    }

    /**
     * 实例化接口数据
     * @return
     */
    public static Map<String,Object> result(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",0);
        map.put("data","");
        map.put("msg","数据返回成功");
        return map;
    }

    /**
     * 创建Gson实例
     * @return
     */
    public static Gson create() {
        GsonBuilder gb = new GsonBuilder();
        //gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        //gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        Gson gson = gb.serializeNulls().create();
        return gson;
    }

    /**
     * 实例化Gon
     */
    private static Gson gson = create();

    /**
     * 对象转JSON
     * @param object
     * @return
     */
    public static String toJSON(Object object){

        return gson.toJson(object);
    }

    /**
     * JSON转对象
     * @param json
     * @return
     */
    public static Map<String,Object> fromJSON(String json){
        Map<String,Object> map = parseData(json);
        return map;
    }

    /**
     * JSON 转 对象
     * @param str
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * 字符串实例化Map对象
     * 函数描述: 将json字符串转换为map
     * @param data
     * @return
     */
    private static Map<String, Object> parseData(String data){
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, Object> map = g.fromJson(data, new TypeToken<Map<String, Object>>() {}.getType());
        return map;
    }

    public static void main(String args[]) {
        String md5 = new Md5Hash("111111","rbcb").toString();
        System.out.println(md5);
    }
}
