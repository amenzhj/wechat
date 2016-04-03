package com.here2u.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.here2u.domain.Remind;
import com.here2u.util.DateUtil;
import com.here2u.weixin.pojo.Token;
import com.here2u.weixin.pojo.data.DataRemind;
import com.here2u.weixin.pojo.data.DataConfname;
import com.here2u.weixin.pojo.data.DataDescription;
import com.here2u.weixin.pojo.data.DataEnd;
import com.here2u.weixin.pojo.data.DataFirst;
import com.here2u.weixin.pojo.data.DataRemark;
import com.here2u.weixin.pojo.data.DataStart;
import com.here2u.weixin.pojo.data.DataTitle;
import com.here2u.weixin.pojo.data.TemplateNews;

/**
 * 通用工具类
 * 
 * @author Joki
 * @version [V1.00, 2016年3月31日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class CommonUtil
{
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
    
    // 凭证获取（http请求方式: GET）
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    
    /**
     * 发送https请求
     * 
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr)
    {
        JSONObject jsonObject = null;
        try
        {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setSSLSocketFactory(ssf);
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr)
            {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null)
            {
                buffer.append(str);
            }
            
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        }
        catch (ConnectException ce)
        {
            log.error("连接超时：{}", ce);
        }
        catch (Exception e)
        {
            log.error("https请求异常：{}", e);
        }
        return jsonObject;
    }
    
    /**
     * 获取接口访问凭证
     * 
     * @param appid 第三方用户唯一凭证
     * @param appsecret 第三方用户唯一凭证密钥
     * @return
     */
    public static Token getToken(String appid, String appsecret)
    {
        Token token = null;
        String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        
        if (null != jsonObject)
        {
            try
            {
                token = new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            }
            catch (JSONException e)
            {
                token = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return token;
    }
    
    /**
     * URL编码（utf-8）
     * 
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source)
    {
        String result = source;
        try
        {
            result = java.net.URLEncoder.encode(source, "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 根据内容类型判断文件扩展名
     * 
     * @param contentType 内容类型
     * @return
     */
    public static String getFileExt(String contentType)
    {
        String fileExt = "";
        if ("image/jpeg".equals(contentType))
            fileExt = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileExt = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileExt = ".amr";
        else if ("video/mp4".equals(contentType))
            fileExt = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileExt = ".mp4";
        return fileExt;
    }
    
    /**
     * 微信公众平台开发之模板消息(Java)
     * 
     * 
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param remind
     * @see [类、类#方法、类#成员]
     */
    public static void sendTemplateMessage(String appId, String appSecret, Remind remind)
    {
        
        Token token = CommonUtil.getToken(appId, appSecret);
        String access_token = token.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;

        TemplateNews temp = new TemplateNews();

        DataRemind data = new DataRemind();

        DataFirst first = new DataFirst();
        
        DataTitle title = new DataTitle();
        
        DataStart start = new DataStart();
        
        DataEnd end = new DataEnd();
        
        DataConfname confname = new DataConfname();
        
        DataDescription description = new DataDescription();
        
        DataRemark remark = new DataRemark();
        
        first.setValue("你有一条新提醒：");
        first.setColor("#173177");
        
        title.setValue(remind.getTitle());
        title.setColor("#173177");
        
        start.setValue(DateUtil.format(remind.getStart()));
        start.setColor("#173177");
        
        end.setValue(DateUtil.format(remind.getEnd()));
        end.setColor("#173177");
        
        confname.setValue(remind.getConfname());
        confname.setColor("#173177");
        
        description.setValue(remind.getDescription());
        description.setColor("#173177");
        
        remark.setValue("祝您生活愉快！");
        remark.setColor("#173177");
        
        data.setFirst(first);
        data.setTitle(title);
        data.setStart(start);
        data.setEnd(end);
        data.setConfname(confname);
        data.setDescription(description);
        data.setRemark(remark);
        temp.setTouser(remind.getUserId());
        temp.setTemplate_id("8P9r4PLMnflT9gGFaUcgAEw8t_2X1RWzvSy_Z-Xb0NM");
        temp.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0c9c9660e38ec1d2&redirect_uri=http%3A%2F%2Fhere2u.tunnel.qydev.com%2Fwechat%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        temp.setTopcolor("#173177");
        temp.setData(data);
        
        String jsonString = JSONObject.fromObject(temp).toString();
        System.out.println(jsonString);
        JSONObject jsonObject = httpsRequest(url, "POST", jsonString);
        System.out.println(jsonObject);
        int result = 0;
        if (null != jsonObject)
        {
            if (0 != jsonObject.getInt("errcode"))
            {
                result = jsonObject.getInt("errcode");
                log.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        log.info("模板消息发送结果：" + result);
    }
}