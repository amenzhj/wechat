package com.here2u.weixin.message.resp;

/**
 * 图片消息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class ImageMessage extends BaseMessage
{
    /**
     * 图片
     */
    private Image Image;

    public Image getImage()
    {
        return Image;
    }

    public void setImage(Image image)
    {
        Image = image;
    }
}
