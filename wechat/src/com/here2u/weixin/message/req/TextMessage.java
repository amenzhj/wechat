package com.here2u.weixin.message.req;

/**
 * 文本消息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class TextMessage extends BaseMessage
{
    /**
     * 消息内容
     */
    private String Content;

    public String getContent()
    {
        return Content;
    }

    public void setContent(String content)
    {
        Content = content;
    }
}
