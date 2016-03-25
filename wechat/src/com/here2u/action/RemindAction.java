package com.here2u.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.here2u.domain.Remind;
import com.here2u.domain.Remindx;
import com.here2u.service.RemindServiceI;
import com.here2u.util.DateUtil;
import com.here2u.util.JsonUtil;

public class RemindAction extends BaseAction
{
    @Autowired
    private RemindServiceI remindService;
    
    public String addRemind()
        throws ParseException
    {
        String title = getRequest().getParameter("title");
        String fullname = getRequest().getParameter("fullname");
        String description = getRequest().getParameter("description");
        String confname = getRequest().getParameter("confname");
        String confshortname = getRequest().getParameter("confshortname");
        String start = getRequest().getParameter("start");
        String end = getRequest().getParameter("end");
        String userId = (String)getSession().get("userId");
        Date dstart = DateUtil.parse(start);
        Date dend = DateUtil.parse(end);
        Remind remind = new Remind(null, title, dstart, dend, confname, description, userId);
        remindService.addRemind(remind);
        return SUCCESS;
    }
    
    public String removeRemind()
        throws NumberFormatException
    {
        String id = getRequest().getParameter("id");
        String userId = (String)getSession().get("userId");
        Remind remind = new Remind();
        remind.setId(Integer.parseInt(id));
        remind.setUserId(userId);
        remindService.removeRemind(remind);
        return SUCCESS;
    }
    
    public String findReminds()
    {
        String start = getRequest().getParameter("start");
        String end = getRequest().getParameter("end");
        String userId = (String)getSession().get("userId");
        Remind remind = new Remind();
        Date dstart = null;
        Date dend = null;
        try
        {
            dstart = DateUtil.parse(start);
            dend = DateUtil.parse(end);
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        remind.setStart(dstart);
        remind.setEnd(dend);
        remind.setUserId(userId);
        List<Remind> reminds = remindService.findReminds(remind);
        List<Remindx> remindList = new ArrayList<Remindx>();
        Remindx remindx=null;
        for (Remind remind2 : reminds)
        {
            Integer id1 = remind2.getId();
            String title1 = remind2.getTitle();
            String start1 = DateUtil.format(remind2.getStart());
            String end1 = DateUtil.format(remind2.getEnd());
            String confname1 = remind2.getConfname();
            String description1 = remind2.getDescription();
            String userId1 = remind2.getUserId();
            remindx = new Remindx(id1, title1, start1, end1, confname1, description1, title1);
            remindList.add(remindx);
        }
        PrintWriter os = null;
        try
        {
            os = getResponse().getWriter();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        os.print(JsonUtil.toJSON(remindList));
        os.flush();
        os.close();
        return SUCCESS;
    }
    
    public String updateRemind()
        throws ParseException
    {
        String title = getRequest().getParameter("title");
        String fullname = getRequest().getParameter("fullname");
        String description = getRequest().getParameter("description");
        String confname = getRequest().getParameter("confname");
        String confshortname = getRequest().getParameter("confshortname");
        String start = getRequest().getParameter("start");
        String end = getRequest().getParameter("end");
        Integer id = Integer.parseInt(getRequest().getParameter("id"));
        String userId = (String)getSession().get("userId");
        Date dstart = DateUtil.parse(start);
        Date dend = DateUtil.parse(end);
        Remind remind = new Remind(id, title, dstart, dend, confname, description, userId);
        remindService.updateRemind(remind);
        return SUCCESS;
    }
}
