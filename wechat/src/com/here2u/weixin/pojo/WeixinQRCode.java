package com.here2u.weixin.pojo;

/**
 * 临时二维码信息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class WeixinQRCode
{
    /**
     * 获取的二维码ticket
     */
    private String ticket;
    
    /**
     * 二维码的有效时间，单位为秒，最大不超过1800
     */
    private int expireSeconds;

    public String getTicket()
    {
        return ticket;
    }

    public void setTicket(String ticket)
    {
        this.ticket = ticket;
    }

    public int getExpireSeconds()
    {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds)
    {
        this.expireSeconds = expireSeconds;
    }
}
