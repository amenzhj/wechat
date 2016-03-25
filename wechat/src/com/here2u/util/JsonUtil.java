package com.here2u.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * <一句话功能简述>
 * 
 * @author Joki
 * @version [V1.00, 2016年3月25日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class JsonUtil
{
    
    public static Object toBean(String json, Class beanClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object bean = JSONObject.toBean(jsonObject, beanClass);
        return bean;
        
    }
    
    public static String toJSON(Object bean)
    {
        JSONObject jsonObject = JSONObject.fromObject(bean);
        String json = jsonObject.toString();
        return json;
    }

    public static List toList(String json, Class beanClass)
    {
        JSONArray jsonObject = JSONArray.fromObject(json);
        List bean = JSONArray.toList(jsonObject, beanClass);
        return bean;
        
    }
    
    
    public static String toJSON(List bean)
    {
        JSONArray jsonObject = JSONArray.fromObject(bean);
        String json = jsonObject.toString();
        return json;
    }
    
   
    
   
    
}
