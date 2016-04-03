package com.here2u.weixin.message.resp;

/**
 * 语音消息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class VoiceMessage extends BaseMessage
{
    /**
     * 语音
     */
    private Voice Voice;

    public Voice getVoice()
    {
        return Voice;
    }

    public void setVoice(Voice voice)
    {
        Voice = voice;
    }
}
