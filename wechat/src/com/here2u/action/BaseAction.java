package com.here2u.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
    private ServletContext application;
    
    private HttpServletRequest request;
    
    private HttpServletResponse response;
    
    private Map<String, Object> session;
    
    public ServletContext getApplication()
    {
        return application;
    }
    
    public void setApplication(ServletContext application)
    {
        this.application = application;
    }
    
    public HttpServletRequest getRequest()
    {
        return request;
    }
    
    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
    public HttpServletResponse getResponse()
    {
        return response;
    }
    
    public void setResponse(HttpServletResponse response)
    {
        this.response = response;
    }
    
    public Map<String, Object> getSession()
    {
        return session;
    }
    
    @Override
    public void setServletContext(ServletContext context)
    {
        this.application = context;
    }
    
    @Override
    public void setSession(Map<String, Object> map)
    {
        this.session = map;
    }
    
    @Override
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
}
