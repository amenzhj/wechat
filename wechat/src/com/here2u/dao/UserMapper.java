package com.here2u.dao;

import java.util.List;

import com.here2u.domain.User;


public interface UserMapper
{
    int deleteByPrimaryKey(Integer id);
    
    int insert(User record);
    
    int insertSelective(User record);
    
    User selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(User record);
    
    int updateByPrimaryKey(User record);
    
    /**
     * 获取所有用户信息
     * 
     * @return List<User>
     */
    List<User> getAllUser();
    
    User queryUser(String userId);
    
    int updateByUserId(User record);
    
}