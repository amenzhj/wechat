package com.here2u.service;

import java.util.List;

import com.here2u.domain.Remind;

public interface RemindServiceI
{
    void addRemind(Remind remind);
    
    void removeRemind(Remind remind);
    
    List<Remind> findReminds(Remind remind);

    void updateRemind(Remind remind);
    
    Remind findRemind(Integer id);
}
