package com.here2u.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.here2u.weixin.message.resp.Article;
import com.here2u.weixin.message.resp.NewsMessage;
import com.here2u.weixin.message.resp.TextMessage;
import com.here2u.weixin.pojo.BaiduPlace;
import com.here2u.weixin.pojo.UserLocation;
import com.here2u.weixin.util.BaiduMapUtil;
import com.here2u.weixin.util.MessageUtil;
import com.here2u.weixin.util.MySQLUtil;

/**
 * 核心服务类
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class CoreService
{
    /**
     * 处理微信发来的请求
     * 
     * @param request
     * @return xml
     */
    public static String processRequest(HttpServletRequest request)
    {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try
        {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            
            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT))
            {
                respContent = "您发送的是文本消息！";
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE))
            {
                respContent = "您发送的是图片消息！";
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE))
            {
                respContent = "您发送的是语音消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO))
            {
                respContent = "您发送的是视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION))
            {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK))
            {
                respContent = "您发送的是链接消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT))
            {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE))
                {
                    respContent = "谢谢您的关注！";
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE))
                {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN))
                {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION))
                {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK))
                {
                    // TODO 处理菜单点击事件
                }
            }
            // 设置文本消息的内容
            textMessage.setContent(respContent);
            // 将文本消息对象转换成xml
            respXml = MessageUtil.messageToXml(textMessage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return respXml;
    }
    
    /**
     * 处理微信发来的请求
     * 
     * @param request
     * @return xml
     */
    public static String processRequest1(HttpServletRequest request)
    {
        // xml格式的消息数据
        String respXml = null;
        try
        {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            // 事件推送
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT))
            {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE))
                {
                    textMessage.setContent("您好，欢迎关注网址导航！我们致力于打造精品网址聚合应用，为用户提供便捷的上网导航服务。体验生活，从这里开始！");
                    // 将消息对象转换成xml
                    respXml = MessageUtil.messageToXml(textMessage);
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE))
                {
                    // TODO 暂不做处理
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK))
                {
                    // 事件KEY值，与创建菜单时的key值对应
                    String eventKey = requestMap.get("EventKey");
                    // 根据key值判断用户点击的按钮
                    if (eventKey.equals("oschina"))
                    {
                        Article article = new Article();
                        article.setTitle("开源中国");
                        article.setDescription("开源中国社区成立于2008年8月，是目前中国最大的开源技术社区。\n\n开源中国的目的是为中国的IT技术人员提供一个全面的、快捷更新的用来检索开源软件以及交流开源经验的平台。\n\n经过不断的改进,目前开源中国社区已经形成了由开源软件库、代码分享、资讯、讨论区和博客等几大频道内容。");
                        article.setPicUrl("");
                        article.setUrl("http://m.oschina.net");
                        List<Article> articleList = new ArrayList<Article>();
                        articleList.add(article);
                        // 创建图文消息
                        NewsMessage newsMessage = new NewsMessage();
                        newsMessage.setToUserName(fromUserName);
                        newsMessage.setFromUserName(toUserName);
                        newsMessage.setCreateTime(new Date().getTime());
                        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                        newsMessage.setArticleCount(articleList.size());
                        newsMessage.setArticles(articleList);
                        respXml = MessageUtil.messageToXml(newsMessage);
                    }
                    else if (eventKey.equals("iteye"))
                    {
                        textMessage.setContent("ITeye即创办于2003年9月的JavaEye,从最初的以讨论Java技术为主的技术论坛，已经逐渐发展成为涵盖整个软件开发领域的综合性网站。\n\nhttp://www.iteye.com");
                        respXml = MessageUtil.messageToXml(textMessage);
                    }
                }
            }
            // 当用户发消息时
            else
            {
                textMessage.setContent("请通过菜单使用网址导航服务！");
                respXml = MessageUtil.messageToXml(textMessage);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return respXml;
    }
    
    /**
     * 处理微信发来的请求
     * 
     * @param request
     * @return xml
     */
    public static String processRequest2(HttpServletRequest request)
    {
        // xml格式的消息数据
        String respXml = null;
        
        try
        {
            // 公众账号标识
            String accountId = request.getParameter("accountId");
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            
            // 事件推送
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT))
            {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE))
                {
                    // 判断公众账号标识
                    if ("aaa".equals(accountId))
                        textMessage.setContent("您好，欢迎关注企业A的官方微信！\n\n我是客服小A，有问题问小A！");
                    else if ("bbb".equals(accountId))
                        textMessage.setContent("嗨，恭喜你终于找到组织了！\n\n我是客服小B，有问题直接问我哦！");
                    respXml = MessageUtil.messageToXml(textMessage);
                }
                else
                {
                    // TODO 其他情况
                }
            }
            else
            {
                // TODO 其他情况
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return respXml;
    }
    
    /**
     * 处理微信发来的请求
     * 
     * @param request
     * @return xml
     */
    public static String processRequest3(HttpServletRequest request)
    {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = null;
        try
        {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            
            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT))
            {
                String content = requestMap.get("Content").trim();
                if (content.equals("附近"))
                {
                    respContent = getUsage();
                }
                // 周边搜索
                else if (content.startsWith("附近"))
                {
                    String keyWord = content.replaceAll("附近", "").trim();
                    // 获取用户最后一次发送的地理位置
                    UserLocation location = MySQLUtil.getLastLocation(request, fromUserName);
                    // 未获取到
                    if (null == location)
                    {
                        respContent = getUsage();
                    }
                    else
                    {
                        // 根据转换后（纠偏）的坐标搜索周边POI
                        List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord, location.getBd09Lng(), location.getBd09Lat());
                        // 未搜索到POI
                        if (null == placeList || 0 == placeList.size())
                        {
                            respContent = String.format("/难过，您发送的位置附近未搜索到“%s”信息！", keyWord);
                        }
                        else
                        {
                            List<Article> articleList = BaiduMapUtil.makeArticleList(placeList, location.getBd09Lng(), location.getBd09Lat());
                            // 回复图文消息
                            NewsMessage newsMessage = new NewsMessage();
                            newsMessage.setToUserName(fromUserName);
                            newsMessage.setFromUserName(toUserName);
                            newsMessage.setCreateTime(new Date().getTime());
                            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                            newsMessage.setArticles(articleList);
                            newsMessage.setArticleCount(articleList.size());
                            respXml = MessageUtil.messageToXml(newsMessage);
                        }
                    }
                }
                else
                    respContent = getUsage();
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION))
            {
                // 用户发送的经纬度
                String lng = requestMap.get("Location_Y");
                String lat = requestMap.get("Location_X");
                // 坐标转换后的经纬度
                String bd09Lng = null;
                String bd09Lat = null;
                // 调用接口转换坐标
                UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
                if (null != userLocation)
                {
                    bd09Lng = userLocation.getBd09Lng();
                    bd09Lat = userLocation.getBd09Lat();
                }
                // 保存用户地理位置
                MySQLUtil.saveUserLocation(request, fromUserName, lng, lat, bd09Lng, bd09Lat);
                
                StringBuffer buffer = new StringBuffer();
                buffer.append("[愉快]").append("成功接收您的位置！").append("\n\n");
                buffer.append("您可以输入搜索关键词获取周边信息了，例如：").append("\n");
                buffer.append("        附近ATM").append("\n");
                buffer.append("        附近KTV").append("\n");
                buffer.append("        附近厕所").append("\n");
                buffer.append("必须以“附近”两个字开头！");
                respContent = buffer.toString();
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT))
            {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE))
                {
                    respContent = getSubscribeMsg();
                }
            }
            else
            {
                respContent = getUsage();
            }
            if (null != respContent)
            {
                // 设置文本消息的内容
                textMessage.setContent(respContent);
                // 将文本消息对象转换成xml
                respXml = MessageUtil.messageToXml(textMessage);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return respXml;
    }
    
    /**
     * 关注提示语
     * 
     * @return
     */
    private static String getSubscribeMsg()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("您是否有过出门在外四处找ATM或厕所的经历？").append("\n\n");
        buffer.append("您是否有过出差在外搜寻美食或娱乐场所的经历？").append("\n\n");
        buffer.append("周边搜索为您的出行保驾护航，为您提供专业的周边生活指南，回复“附近”开始体验吧！");
        return buffer.toString();
    }
    
    /**
     * 使用说明
     * 
     * @return
     */
    private static String getUsage()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("周边搜索使用说明").append("\n\n");
        buffer.append("1）发送地理位置").append("\n");
        buffer.append("点击窗口底部的“+”按钮，选择“位置”，点“发送”").append("\n\n");
        buffer.append("2）指定关键词搜索").append("\n");
        buffer.append("格式：附近+关键词\n例如：附近ATM、附近KTV、附近厕所");
        return buffer.toString();
    }
}
