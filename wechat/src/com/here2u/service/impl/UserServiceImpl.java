package com.here2u.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.here2u.dao.UserMapper;
import com.here2u.domain.User;
import com.here2u.service.UserServiceI;

/**
 * @author joki
 *         使用@Service注解将UserServiceImpl类标注为一个service
 *         service的id是userService
 */

@Service("userService")
public class UserServiceImpl implements UserServiceI
{
    /**
     * 使用@Autowired注解标注userMapper变量，
     * 当需要使用UserMapper时，Spring就会自动注入UserMapper
     */
    @Autowired
    private UserMapper userMapper;// 注入dao
    
    @Override
    public void addUser(User user)
    {
        userMapper.insert(user);
    }
    
    @Override
    public User getUserById(int id)
    {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getAllUser()
    {
        return userMapper.getAllUser();
    }
    
    @Override
    public User findUser(String userId)
    {
        return userMapper.queryUser(userId);
    }
    
    @Override
    public void updateUser(User user)
    {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void updateByUserId(User user)
    {
        userMapper.updateByUserId(user);
        
    }
    
}
