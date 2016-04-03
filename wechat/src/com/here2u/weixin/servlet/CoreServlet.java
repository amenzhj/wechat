package com.here2u.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.here2u.weixin.service.CoreService;
import com.here2u.weixin.util.SignUtil;

/**
 * 验证服务器地址的有效性
 * 
 * @author Joki
 * @version [V1.00, 2016年3月31日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class CoreServlet extends HttpServlet
{
    private static final long serialVersionUID = 4440739483644821986L;
    
    /**
     * 请求校验（确认请求来自微信服务器）
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        
        PrintWriter out = response.getWriter();
        // 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce))
        {
            out.print(echostr);
        }
        out.close();
        out = null;
    }
    
    /**
     * 处理微信服务器发来的消息
     * 微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带四个参数
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        
        PrintWriter out = response.getWriter();
        // 请求校验
        if (SignUtil.checkSignature(signature, timestamp, nonce))
        {
            // 调用核心服务类接收处理请求
            String respXml = CoreService.processRequest(request);
            out.print(respXml);
        }
        out.close();
        out = null;
    }
}
