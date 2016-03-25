package com.here2u.weixin.servlet;

import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.here2u.util.DateUtil;
import com.here2u.weixin.pojo.Token;
import com.here2u.weixin.util.CommonUtil;

public class InitServlet extends HttpServlet
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4059722882869351192L;

    public static String APPID = null;
    
    public static String APPSECRET = null;
    
    /**
     * 微信服务器接口令牌
     */
    public static String BASETOKEN = null;

    public static Token TOKEN = null;

    /**
     * Constructor of the object.
     */
    public InitServlet()
    {
        super();
    }
    
    @Override
    public void init(ServletConfig config)
        throws ServletException
    {
        APPID = config.getInitParameter("APPID");
        APPSECRET = config.getInitParameter("APPSECRET");
        BASETOKEN = config.getInitParameter("BASETOKEN");
        System.out.println(DateUtil.format(new Date()) + InitServlet.APPID + "=======" + InitServlet.APPSECRET + "=======" + BASETOKEN);

        TOKEN = CommonUtil.getToken(InitServlet.APPID, InitServlet.APPSECRET);
        System.out.println("TOKEN:" + InitServlet.TOKEN);
    }
}
