package com.here2u.weixin.message.req;

/**
 * 视频消息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class VideoMessage extends BaseMessage
{
    /**
     * 视频消息媒体id
     */
    private String MediaId;
    
    /**
     * 视频消息缩略图的媒体id
     */
    private String ThumbMediaId;

    public String getMediaId()
    {
        return MediaId;
    }

    public void setMediaId(String mediaId)
    {
        MediaId = mediaId;
    }

    public String getThumbMediaId()
    {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId)
    {
        ThumbMediaId = thumbMediaId;
    }
}
