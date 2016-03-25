package com.here2u.domain;

import java.io.Serializable;

public class Remindx implements Serializable
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6834354012330678895L;

    private Integer id;
    
    private String title;
    
    private String start;
    
    private String end;
    
    private String confname;
    
    private String description;
    
    private String userId;
    
    private String fullname;


    public Remindx(Integer id, String title, String start, String end, String confname, String description, String fullname)
    {
        super();
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.confname = confname;
        this.description = description;
        this.fullname = fullname;
    }
    
    public Remindx()
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
    
    public String getStart()
    {
        return start;
    }
    
    public void setStart(String start)
    {
        this.start = start;
    }
    
    public String getEnd()
    {
        return end;
    }
    
    public void setEnd(String end)
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
    
    public String getFullname()
    {
        return fullname;
    }
    
    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    @Override
    public String toString()
    {
        return "Remind [id=" + id + ", title=" + title + ", start=" + start + ", end=" + end + ", confname=" + confname + ", description=" + description + ", userId=" + userId + "]";
    }

}