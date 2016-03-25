package com.here2u.domain;

import java.io.Serializable;
import java.util.Date;

public class Remind implements Serializable
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6834354012330678895L;

    private Integer id;
    
    private String title;
    
    private Date start;
    
    private Date end;
    
    private String confname;
    
    private String description;
    
    private String userId;
    
    public Remind(Integer id, String title, Date start, Date end, String confname, String description, String userId)
    {
        super();
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.confname = confname;
        this.description = description;
        this.userId = userId;
    }
    
    public Remind()
    {
        super();
    }

    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title == null ? null : title.trim();
    }
    
    public Date getStart()
    {
        return start;
    }
    
    public void setStart(Date start)
    {
        this.start = start;
    }
    
    public Date getEnd()
    {
        return end;
    }
    
    public void setEnd(Date end)
    {
        this.end = end;
    }
    
    public String getConfname()
    {
        return confname;
    }
    
    public void setConfname(String confname)
    {
        this.confname = confname == null ? null : confname.trim();
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description == null ? null : description.trim();
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId == null ? null : userId.trim();
    }

    @Override
    public String toString()
    {
        return "Remind [id=" + id + ", title=" + title + ", start=" + start + ", end=" + end + ", confname=" + confname + ", description=" + description + ", userId=" + userId + "]";
    }

}