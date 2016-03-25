package com.here2u.service;

import java.util.List;

import com.here2u.domain.User;

public interface UserServiceI
{
    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);
    
    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    User getUserById(int id);
    
    /**获取所有用户信息
     * @return List<User>
     */
    List<User> getAllUser();
    
    User findUser(String userId);
    
    void updateUser(User user);
    
    void updateByUserId(User user);
}
