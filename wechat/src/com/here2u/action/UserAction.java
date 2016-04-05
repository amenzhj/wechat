package com.here2u.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.here2u.domain.User;
import com.here2u.service.UserServiceI;
import com.here2u.weixin.pojo.SNSUserInfo;

/**
 * 用户action
 * 
 * @author Joki
 * @version [V1.00, 2016年4月4日]
 * @see [相关类/方法]
 * @since V1.00
 */
@Controller
public class UserAction extends BaseAction
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -320421107466117997L;
    
    @Autowired
    private UserServiceI userService;
    
    /**
     * 用户
     */
    private User user;

    /**
     * 用户登录
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String login()
    {
        String userId = getRequest().getParameter("userId");
        // 根据userId查询用户
        user = userService.findUser(userId);
        if (user != null && user.getRemindSetting() == true)
        {
            // 将userId放入session
            getSession().put("userId", userId);
            return SUCCESS;
        }
        return INPUT;
    }
    
    /**
     * 用户注册
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String registe()
    {
        String userId = getRequest().getParameter("userId");
        // 根据userId查询用户
        user = userService.findUser(userId);
        // 用户存在
        if (user != null && user.getRemindSetting() == false)
        {
            // 启用提醒功能
            user.setRemindSetting(true);
            // 修改用户
            userService.updateUser(user);
            return SUCCESS;
        }
        // 用户不存在
        else
        {
            user = new User();
            user.setUserId(userId);
            user.setRemindSetting(true);
            // 添加用户
            userService.addUser(user);
            return SUCCESS;
        }
    }
    
    /**
     * 与微信对接，绑定用户
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String bind()
    {
        // 获得snsUserInfo
        SNSUserInfo snsUserInfo = (SNSUserInfo)getRequest().getAttribute("snsUserInfo");
        String userId = snsUserInfo.getOpenId();
        // 根据userId查询用户
        user = userService.findUser(userId);
        // 用户存在
        if (user != null && user.getRemindSetting() == true)
        {
            // 将userId放入session
            getSession().put("userId", userId);
            return SUCCESS;
        }
        // 用户存在,提醒功能未开启
        if (user != null && user.getRemindSetting() == false)
        {
            user.setRemindSetting(true);
            // 修改用户
            userService.updateUser(user);
            // 将userId放入session
            getSession().put("userId", userId);
            return SUCCESS;
        }
        // 用户不存在
        else
        {
            user = new User();
            user.setUserId(userId);
            user.setRemindSetting(true);
            // 添加用户
            userService.addUser(user);
            // 将userId放入session
            getSession().put("userId", userId);
            return SUCCESS;
        }
    }
    
    /**
     * 取消提醒
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String unfollow()
    {
        user = new User();
        // 从session中拿出userId
        String userId = (String)getSession().get("userId");
        user.setUserId(userId);
        user.setRemindSetting(false);
        // 根据userId修改用户信息
        userService.updateByUserId(user);
        return SUCCESS;
    }
}
