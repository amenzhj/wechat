package com.here2u.weixin.pojo.data;

/**
 * remind数据model
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class DataRemind
{
    /**
     * 第一句话
     */
    private DataFirst first;
    
    /**
     * 标题
     */
    private DataTitle title;
    
    /**
     * 开始时间
     */
    private DataStart start;
    
    /**
     * 结束时间
     */
    private DataEnd end;
    
    /**
     * 重要程度
     */
    private DataConfname confname;
    
    /**
     * 描述
     */
    private DataDescription description;
    
    /**
     * 末尾用户自行添加多行需要的内容
     */
    private DataRemark remark;
    
    public DataFirst getFirst()
    {
        return first;
    }
    
    public void setFirst(DataFirst first)
    {
        this.first = first;
    }
    
    public DataTitle getTitle()
    {
        return title;
    }
    
    public void setTitle(DataTitle title)
    {
        this.title = title;
    }
    
    public DataStart getStart()
    {
        return start;
    }
    
    public void setStart(DataStart start)
    {
        this.start = start;
    }
    
    public DataEnd getEnd()
    {
        return end;
    }
    
    public void setEnd(DataEnd end)
    {
        this.end = end;
    }
    
    public DataConfname getConfname()
    {
        return confname;
    }
    
    public void setConfname(DataConfname confname)
    {
        this.confname = confname;
    }
    
    public DataDescription getDescription()
    {
        return description;
    }
    
    public void setDescription(DataDescription description)
    {
        this.description = description;
    }
    
    public DataRemark getRemark()
    {
        return remark;
    }
    
    public void setRemark(DataRemark remark)
    {
        this.remark = remark;
    }
    
    @Override
    public String toString()
    {
        return "Data [first=" + first + ", title=" + title + ", start=" + start + ", end=" + end + ", confname=" + confname + ", description=" + description + ", remark=" + remark + "]";
    }
    
}
