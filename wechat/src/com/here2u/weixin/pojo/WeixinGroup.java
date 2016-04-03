package com.here2u.weixin.pojo;

/**
 * 公众账号分组信息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class WeixinGroup
{
    /**
     * 分组id，由微信分配
     */
    private int id;
    
    /**
     * 分组名字，UTF8编码
     */
    private String name;
    
    /**
     * 分组内的用户数
     */
    private int count;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
