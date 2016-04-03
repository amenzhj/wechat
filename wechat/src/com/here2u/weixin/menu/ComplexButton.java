package com.here2u.weixin.menu;

/**
 * 复合类型的按钮
 * 
 * @author Joki
 * @version [V1.00, 2016年4月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class ComplexButton extends Button
{
    private Button[] sub_button;

    public Button[] getSub_button()
    {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button)
    {
        this.sub_button = sub_button;
    }
}
