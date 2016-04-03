package com.here2u.weixin.message.resp;

/**
 * 视频消息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class VideoMessage extends BaseMessage
{
    /**
     * 视频
     */
    private Video Video;

    public Video getVideo()
    {
        return Video;
    }

    public void setVideo(Video video)
    {
        Video = video;
    }
}
