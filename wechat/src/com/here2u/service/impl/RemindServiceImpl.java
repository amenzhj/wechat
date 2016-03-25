package com.here2u.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.here2u.dao.RemindMapper;
import com.here2u.domain.Remind;
import com.here2u.service.RemindServiceI;

@Service
public class RemindServiceImpl implements RemindServiceI
{
    @Autowired
    private RemindMapper remindMapper;
    
    @Override
    public void addRemind(Remind remind)
    {
        remindMapper.insert(remind);
    }
    
    @Override
    public void removeRemind(Remind remind)
    {
        remindMapper.deleteRemind(remind);
    }
    
    @Override
    public List<Remind> findReminds(Remind remind)
    {
        List<Remind> reminds = remindMapper.selectReminds(remind);
        return reminds;
    }
    
    @Override
    public void updateRemind(Remind remind)
    {
        remindMapper.updateByPrimaryKey(remind);
        
    }
    
    @Override
    public Remind findRemind(Integer id)
    {
        return remindMapper.selectByPrimaryKey(id);
    }
    
}
