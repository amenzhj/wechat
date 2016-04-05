package com.here2u.domain;

import java.io.Serializable;

/**
 * 日历用户model
 * 
 * @author Joki
 * @version [V1.00, 2016年4月4日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class User implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1721471836632994721L;
    
    /**
     * id
     */
    private Integer id;
    
    /**
     * userId
     */
    private String userId;
    
    /**
     * 提醒设置
     */
    private Boolean remindSetting;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId == null ? null : userId.trim();
    }
    
    public Boolean getRemindSetting()
    {
        return remindSetting;
    }
    
    public void setRemindSetting(Boolean remindSetting)
    {
        this.remindSetting = remindSetting;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", userId=" + userId + ", remindSetting=" + remindSetting + "]";
    }
    
}