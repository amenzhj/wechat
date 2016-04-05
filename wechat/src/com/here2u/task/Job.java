package com.here2u.task;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.here2u.domain.Remind;
import com.here2u.service.RemindServiceI;
import com.here2u.util.DateUtil;
import com.here2u.weixin.servlet.InitServlet;
import com.here2u.weixin.util.CommonUtil;


/**
 * 定时任务
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class Job
{
    /**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(Job.class);
    @Autowired
    private RemindServiceI remindService;
    
    /**
     * 定时刷新凭证token
     * <功能详细描述>
     * 
     * @see [类、类#方法、类#成员]
     */
    public void refreshToken()
    {
        log.info("Job.refreshToken()", DateUtil.format(new Date()) + InitServlet.APPID + InitServlet.APPSECRET);
        // System.out.println(DateUtil.format(new Date()) + InitServlet.APPID + InitServlet.APPSECRET);
        // 获得token凭证
        InitServlet.TOKEN = CommonUtil.getToken(InitServlet.APPID, InitServlet.APPSECRET);
        // System.out.println(InitServlet.TOKEN);
        log.info("Job.refreshToken()", "TOKEN:" + InitServlet.TOKEN);
    }
    
    /**
     * 定时查询提醒数据模板推送
     * <功能详细描述>
     * 
     * @see [类、类#方法、类#成员]
     */
    public void findReminds()
    {
        // 查询所有user所对应的remind
        List<Remind> remindList = remindService.findUser2Reminds();
        // System.out.println(remindList);
        log.info("remindList", remindList + "");
        // TODO
        // 第三方用户唯一凭证
        String appId = "wx0c9c9660e38ec1d2";
        // TODO
        // 第三方用户唯一凭证密钥
        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
        // 推送模板消息
        for (Remind remind : remindList)
        {
            // CommonUtil.sendTemplateMessage(InitServlet.APPID, InitServlet.APPSECRET, remind);
            CommonUtil.sendTemplateMessage(appId, appSecret, remind);
        }
    }
}
