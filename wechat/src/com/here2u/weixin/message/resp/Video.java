package com.here2u.weixin.message.resp;

/**
 * 视频model
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class Video
{
    /**
     * 媒体文件id
     */
    private String MediaId;
    
    /**
     * 缩略图的媒体id
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
