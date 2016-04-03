package com.here2u.weixin.message.req;

/**
 * 图片消息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class ImageMessage extends BaseMessage
{
    /**
     * 图片链接
     */
    private String PicUrl;

    public String getPicUrl()
    {
        return PicUrl;
    }

    public void setPicUrl(String picUrl)
    {
        PicUrl = picUrl;
    }
}
