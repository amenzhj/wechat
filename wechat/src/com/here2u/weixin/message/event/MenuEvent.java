package com.here2u.weixin.message.event;

/**
 * 自定义菜单事件
 * 
 * @author Joki
 * @version [V1.00, 2016年4月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class MenuEvent extends BaseEvent
{
    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    private String EventKey;

    public String getEventKey()
    {
        return EventKey;
    }

    public void setEventKey(String eventKey)
    {
        EventKey = eventKey;
    }
}
