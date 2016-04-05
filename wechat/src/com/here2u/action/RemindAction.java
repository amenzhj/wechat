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

/**
 * 日历提醒action
 * 
 * @author Joki
 * @version [V1.00, 2016年4月4日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class RemindAction extends BaseAction
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8190207916511861366L;

    @Autowired
    private RemindServiceI remindService;
    
    /**
     * 标题
     */
    private String title;
    
    /**
     * 标题名
     */
    private String fullname;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 重要程度
     */
    private String confname;
    
    /**
     * 昵称
     */
    private String confshortname;
    
    /**
     * 开始时间
     */
    private String start;
    
    /**
     * 结束时间
     */
    private String end;
    
    /**
     * userId
     */
    private String userId;
    
    /**
     * 开始时间 Date
     */
    private Date dstart;
    
    /**
     * 结束时间 Date
     */
    private Date dend;
    
    /**
     * 提醒
     */
    private Remind remind;
    
    /**
     * 增加提醒
     * 
     * @return
     * @throws ParseException
     * @see [类、类#方法、类#成员]
     */
    public String addRemind()
        throws ParseException
    {
        title = getRequest().getParameter("title");
        fullname = getRequest().getParameter("fullname");
        description = getRequest().getParameter("description");
        confname = getRequest().getParameter("confname");
        confshortname = getRequest().getParameter("confshortname");
        start = getRequest().getParameter("start");
        end = getRequest().getParameter("end");
        userId = (String)getSession().get("userId");
        dstart = DateUtil.parse(start);
        dend = DateUtil.parse(end);
        remind = new Remind(null, title, dstart, dend, confname, description, userId);
        // 增加日历提醒
        remindService.addRemind(remind);
        return SUCCESS;
    }
    
    /**
     * 删除提醒
     * 
     * @return
     * @throws NumberFormatException
     * @see [类、类#方法、类#成员]
     */
    public String removeRemind()
        throws NumberFormatException
    {
        String id = getRequest().getParameter("id");
        userId = (String)getSession().get("userId");
        remind = new Remind();
        remind.setId(Integer.parseInt(id));
        remind.setUserId(userId);
        // 删除日历提醒
        remindService.removeRemind(remind);
        return SUCCESS;
    }
    
    /**
     * 查询提醒
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String findReminds()
    {
        start = getRequest().getParameter("start");
        end = getRequest().getParameter("end");
        userId = (String)getSession().get("userId");
        remind = new Remind();
        dstart = null;
        dend = null;
        try
        {
            // 日期转换
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
        // 查询日历提醒
        List<Remind> reminds = remindService.findReminds(remind);
        List<Remindx> remindList = new ArrayList<Remindx>();
        Remindx remindx = null;
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
    
    /**
     * 修改提醒
     * 
     * @return
     * @throws ParseException
     * @see [类、类#方法、类#成员]
     */
    public String updateRemind()
        throws ParseException
    {
        title = getRequest().getParameter("title");
        fullname = getRequest().getParameter("fullname");
        description = getRequest().getParameter("description");
        confname = getRequest().getParameter("confname");
        confshortname = getRequest().getParameter("confshortname");
        start = getRequest().getParameter("start");
        end = getRequest().getParameter("end");
        Integer id = Integer.parseInt(getRequest().getParameter("id"));
        userId = (String)getSession().get("userId");
        dstart = DateUtil.parse(start);
        dend = DateUtil.parse(end);
        remind = new Remind(id, title, dstart, dend, confname, description, userId);
        // 修改日历提醒
        remindService.updateRemind(remind);
        return SUCCESS;
    }
}
