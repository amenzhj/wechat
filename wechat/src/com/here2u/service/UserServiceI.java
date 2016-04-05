package com.here2u.service;

import java.util.List;

import com.here2u.domain.User;

public interface UserServiceI
{
    /**
     * 添加用户
     * 
     * @param user
     */
    void addUser(User user);
    
    /**
     * 根据用户id获取用户
     * 
     * @param userId
     * @return
     */
    User getUserById(int id);
    
    /**
     * 获取所有用户信息
     * 
     * @return List<User>
     */
    List<User> getAllUser();
    
    /**
     * 根据userId查询用户
     * 
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    User findUser(String userId);
    
    /**
     * 修改用户
     * 
     * @param user
     * @see [类、类#方法、类#成员]
     */
    void updateUser(User user);
    
    /**
     * 根据userId修改用户remindsetting
     * 
     * @param user
     * @see [类、类#方法、类#成员]
     */
    void updateByUserId(User user);
}
