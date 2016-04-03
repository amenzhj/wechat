package com.here2u.weixin.message.req;

/**
 * 语音消息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class VoiceMessage extends BaseMessage
{
    /**
     * 媒体ID
     */
    private String MediaId;
    
    /**
     * 语音格式
     */
    private String Format;
    
    /**
     * 语音识别结果，UTF8编码
     */
    private String Recognition;

    public String getMediaId()
    {
        return MediaId;
    }

    public void setMediaId(String mediaId)
    {
        MediaId = mediaId;
    }

    public String getFormat()
    {
        return Format;
    }

    public void setFormat(String format)
    {
        Format = format;
    }

    public String getRecognition()
    {
        return Recognition;
    }

    public void setRecognition(String recognition)
    {
        Recognition = recognition;
    }
}
