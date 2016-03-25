package com.here2u.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.here2u.domain.Remind;
import com.here2u.service.RemindServiceI;
import com.here2u.util.DateUtil;
import com.here2u.weixin.servlet.InitServlet;
import com.here2u.weixin.util.CommonUtil;

public class Job
{
    

    @Autowired
    private RemindServiceI remindService;
    public void refreshToken()
    {
        
        System.out.println(DateUtil.format(new Date()) + InitServlet.APPID + InitServlet.APPSECRET);
        InitServlet.TOKEN = CommonUtil.getToken(InitServlet.APPID, InitServlet.APPSECRET);
        System.out.println(InitServlet.TOKEN);
    }
    
    public void findReminds()
    {
        List<Remind> remindList = remindService.findUser2Reminds();
        System.out.println(remindList);
    }
}

