package com.here2u.weixin.pojo.data;

public class Data
{
    private DataFirst first;
    
    private DataTitle title;
    
    private DataStart start;
    
    private DataEnd end;
    
    private DataConfname confname;
    
    private DataDescription description;
    
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
