package com.here2u.weixin.message.resp;

/**
 * 音乐消息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class MusicMessage extends BaseMessage
{
    /**
     * 音乐
     */
    private Music Music;

    public Music getMusic()
    {
        return Music;
    }

    public void setMusic(Music music)
    {
        Music = music;
    }
}
