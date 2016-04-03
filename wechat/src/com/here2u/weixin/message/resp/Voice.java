package com.here2u.weixin.message.resp;

/**
 * 语音model
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class Voice
{
    /**
     * 媒体文件id
     */
    private String MediaId;

    public String getMediaId()
    {
        return MediaId;
    }

    public void setMediaId(String mediaId)
    {
        MediaId = mediaId;
    }
}
