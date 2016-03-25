package com.here2u.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.here2u.domain.User;
import com.here2u.service.UserServiceI;

@Controller
public class UserAction extends BaseAction
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -320421107466117997L;
    
    @Autowired
    private UserServiceI userService;
    
    public String login()
    {
        String userId = getRequest().getParameter("userId");
        User user = userService.findUser(userId);
        if (user != null && user.getRemindSetting() == true)
        {
            getSession().put("userId", userId);
            return SUCCESS;
        }
        return INPUT;
    }
    
    public String registe()
    {
        String userId = getRequest().getParameter("userId");
        User user = userService.findUser(userId);
        if (user != null && user.getRemindSetting() == false)
        {
            user.setRemindSetting(true);
            userService.updateUser(user);
            return SUCCESS;
        }
        else
        {
            user = new User();
            user.setUserId(userId);
            user.setRemindSetting(true);
            userService.addUser(user);
            return SUCCESS;
        }
    }
    
    public String unfollow()
    {
        User user = new User();
        String userId=(String)getSession().get("userId");
        user.setUserId(userId);
        user.setRemindSetting(false);
        userService.updateByUserId(user);
        return SUCCESS;
    }
}
