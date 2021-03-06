package com.here2u.weixin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.here2u.weixin.menu.Button;
import com.here2u.weixin.menu.ClickButton;
import com.here2u.weixin.menu.ComplexButton;
import com.here2u.weixin.menu.Menu;
import com.here2u.weixin.menu.ViewButton;
import com.here2u.weixin.pojo.Token;
import com.here2u.weixin.util.CommonUtil;
import com.here2u.weixin.util.MenuUtil;

/**
 * 菜单管理器类
 * 
 * @author Joki
 * @version [V1.00, 2016年4月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class MenuManager
{
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    /**
     * 定义菜单结构
     * 
     * @return
     */
    private static Menu getMenu()
    {
        ClickButton btn11 = new ClickButton();
        btn11.setName("开源中国");
        btn11.setType("click");
        btn11.setKey("oschina");
        
        ClickButton btn12 = new ClickButton();
        btn12.setName("ITeye");
        btn12.setType("click");
        btn12.setKey("iteye");
        
        ViewButton btn13 = new ViewButton();
        btn13.setName("CocoaChina");
        btn13.setType("view");
        btn13.setUrl("http://www.iteye.com");

        ViewButton btn14 = new ViewButton();
        btn14.setName("日历管理");
        btn14.setType("view");
        btn14.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0c9c9660e38ec1d2&redirect_uri=http%3A%2F%2Fhere2u.tunnel.qydev.com%2Fwechat%2FoauthServlet&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        
        ViewButton btn21 = new ViewButton();
        btn21.setName("淘宝");
        btn21.setType("view");
        btn21.setUrl("http://m.taobao.com");
        
        ViewButton btn22 = new ViewButton();
        btn22.setName("京东");
        btn22.setType("view");
        btn22.setUrl("http://m.jd.com");
        
        ViewButton btn23 = new ViewButton();
        btn23.setName("唯品会");
        btn23.setType("view");
        btn23.setUrl("http://m.vipshop.com");
        
        ViewButton btn24 = new ViewButton();
        btn24.setName("当当网");
        btn24.setType("view");
        btn24.setUrl("http://m.dangdang.com");
        
        ViewButton btn25 = new ViewButton();
        btn25.setName("苏宁易购");
        btn25.setType("view");
        btn25.setUrl("http://m.suning.com");
        
        ViewButton btn31 = new ViewButton();
        btn31.setName("多泡");
        btn31.setType("view");
        btn31.setUrl("http://www.duopao.com");
        
        ViewButton btn32 = new ViewButton();
        btn32.setName("一窝88");
        btn32.setType("view");
        btn32.setUrl("http://www.yi588.com");
        
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("技术交流");
        mainBtn1.setSub_button(new Button[] {btn11, btn12, btn13, btn14});
        
        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("购物");
        mainBtn2.setSub_button(new Button[] {btn21, btn22, btn23, btn24, btn25});
        
        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("网页游戏");
        mainBtn3.setSub_button(new Button[] {btn31, btn32});
        
        Menu menu = new Menu();
        menu.setButton(new Button[] {mainBtn1, mainBtn2, mainBtn3});
        
        return menu;
    }

    public static void main(String[] args)
    {
        // 第三方用户唯一凭证
        String appId = "wx0c9c9660e38ec1d2";
        // 第三方用户唯一凭证密钥
        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
        
        // 调用接口获取凭证
        Token token = CommonUtil.getToken(appId, appSecret);
        if (null != token)
        {
            // 创建菜单
            boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());
            
            // 判断菜单创建结果
            if (result)
            {
                System.out.println("菜单创建成功！");
                log.info("菜单创建成功！");
            }
            else
            {
                System.out.println("菜单创建失败！");
                log.info("菜单创建失败！");
            }
        }
    }
}
