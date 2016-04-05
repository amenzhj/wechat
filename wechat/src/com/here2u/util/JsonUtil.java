package com.here2u.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * json转换工具类
 * 
 * @author Joki
 * @version [V1.00, 2016年3月25日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class JsonUtil
{
    /**
     * json字符串转换对象
     * 
     * @param json
     * @param beanClass
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Object toBean(String json, Class beanClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object bean = JSONObject.toBean(jsonObject, beanClass);
        return bean;
        
    }
    
    /**
     * 对象转换jsonObject字符串
     * 
     * @param bean
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String toJSON(Object bean)
    {
        JSONObject jsonObject = JSONObject.fromObject(bean);
        String json = jsonObject.toString();
        return json;
    }

    /**
     * json字符串转换list集合
     * 
     * 
     * @param json
     * @param beanClass
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static List toList(String json, Class beanClass)
    {
        JSONArray jsonObject = JSONArray.fromObject(json);
        List bean = JSONArray.toList(jsonObject, beanClass);
        return bean;
        
    }
    
    /**
     * list集合转换jsonObject字符串
     * 
     * @param bean
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String toJSON(List bean)
    {
        JSONArray jsonObject = JSONArray.fromObject(bean);
        String json = jsonObject.toString();
        return json;
    }
    
}
