package com.here2u.weixin.pojo.data;

/**
 * 模板消息
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class TemplateNews
{
    /**
     * 用户OpenID
     */
    private String touser;
    
    /**
     * 模板消息ID
     */
    private String template_id;
    
    /**
     * URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）。
     */
    private String url;
    
    /**
     * 标题颜色
     */
    private String topcolor;
    
    /**
     * 详细内容
     */
    private DataRemind data;
    
    public String getTouser()
    {
        return touser;
    }
    
    public void setTouser(String touser)
    {
        this.touser = touser;
    }
    
    public String getTemplate_id()
    {
        return template_id;
    }
    
    public void setTemplate_id(String template_id)
    {
        this.template_id = template_id;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getTopcolor()
    {
        return topcolor;
    }
    
    public void setTopcolor(String topcolor)
    {
        this.topcolor = topcolor;
    }
    
    public DataRemind getData()
    {
        return data;
    }
    
    public void setData(DataRemind data)
    {
        this.data = data;
    }
    
    @Override
    public String toString()
    {
        return "NewRemindsTemplate [touser=" + touser + ", template_id=" + template_id + ", url=" + url + ", topcolor=" + topcolor + ", data=" + data + "]";
    }
    
}
