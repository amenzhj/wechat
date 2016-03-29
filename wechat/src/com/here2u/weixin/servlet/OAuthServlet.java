package com.here2u.weixin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.here2u.service.UserServiceI;
import com.here2u.weixin.pojo.SNSUserInfo;
import com.here2u.weixin.pojo.WeixinOauth2Token;
import com.here2u.weixin.util.AdvancedUtil;

/**
 * 授权后的回调请求处理
 * 
 * @author liufeng
 * @date 2013-11-12
 */
public class OAuthServlet extends HttpServlet {
	private static final long serialVersionUID = -1847238807216447030L;
    
    @Autowired
    private UserServiceI userService;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 第三方用户唯一凭证
        String appId = "wx0c9c9660e38ec1d2";
        // 第三方用户唯一凭证密钥
        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
        
        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        
        SNSUserInfo snsUserInfo = null;
        // 用户同意授权
		if (!"authdeny".equals(code)) {
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(appId, appSecret, code);
            // 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
			String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
            snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);

            // 设置要传递的参数
			request.setAttribute("snsUserInfo", snsUserInfo);
		}
        request.getRequestDispatcher("bind.action").forward(request, response);


	}
}
