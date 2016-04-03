package com.here2u.weixin.servlet;

import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.here2u.util.DateUtil;
import com.here2u.weixin.pojo.Token;
import com.here2u.weixin.util.CommonUtil;

/**
 * 
 * servlet初始化读取配置
 * 
 * @author Joki
 * @version [V1.00, 2016年3月31日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class InitServlet extends HttpServlet
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4059722882869351192L;

    /**
     * 第三方用户唯一凭证
     */
    public static String APPID = null;
    
    /**
     * 第三方用户唯一凭证密钥
     */
    public static String APPSECRET = null;
    
    /**
     * 微信服务器接口令牌
     */
    public static String BASETOKEN = null;

    /**
     * 接口访问凭证
     */
    public static Token TOKEN = null;

    /**
     * Constructor of the object.
     */
    public InitServlet()
    {
        super();
    }
    
    /**
     * 初始APPID、APPSECRET、BASETOKEN
     * 
     * @param config
     * @throws ServletException
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config)
        throws ServletException
    {
        APPID = config.getInitParameter("APPID");
        APPSECRET = config.getInitParameter("APPSECRET");
        BASETOKEN = config.getInitParameter("BASETOKEN");
        System.out.println(DateUtil.format(new Date()) + InitServlet.APPID + "=======" + InitServlet.APPSECRET + "=======" + BASETOKEN);
        // 获取接口访问凭证
        TOKEN = CommonUtil.getToken(InitServlet.APPID, InitServlet.APPSECRET);
        System.out.println("TOKEN:" + InitServlet.TOKEN);
    }
}
