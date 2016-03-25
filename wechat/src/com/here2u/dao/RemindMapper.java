package com.here2u.dao;

import java.util.List;

import com.here2u.domain.Remind;

public interface RemindMapper
{
    int deleteByPrimaryKey(Integer id);
    
    int insert(Remind record);
    
    int insertSelective(Remind record);
    
    Remind selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Remind record);
    
    int updateByPrimaryKey(Remind record);
    
    int deleteRemind(Remind record);

    List<Remind> selectReminds(Remind record);
}