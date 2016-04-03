package com.here2u.weixin.pojo.data;

/**
 * 模板基类
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class DataBase
{
    /**
     * 赋值
     */
    private String value;
    
    /**
     * 颜色
     */
    private String color;
    
    public String getValue()
    {
        return value;
    }
    
    public void setValue(String value)
    {
        this.value = value;
    }
    
    public String getColor()
    {
        return color;
    }
    
    public void setColor(String color)
    {
        this.color = color;
    }
}
