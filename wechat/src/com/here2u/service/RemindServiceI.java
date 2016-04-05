package com.here2u.service;

import java.util.List;

import com.here2u.domain.Remind;

/**
 * remind服务
 * 
 * @author Joki
 * @version [V1.00, 2016年4月4日]
 * @see [相关类/方法]
 * @since V1.00
 */
public interface RemindServiceI
{
    /**
     * 增加新的remind
     * 
     * @param remind
     * @see [类、类#方法、类#成员]
     */
    void addRemind(Remind remind);
    
    /**
     * 移除remind
     * 
     * @param remind
     * @see [类、类#方法、类#成员]
     */
    void removeRemind(Remind remind);
    
    /**
     * 查询reminds
     * 
     * @param remind
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Remind> findReminds(Remind remind);
    
    /**
     * 修改remind
     * 
     * @param remind
     * @see [类、类#方法、类#成员]
     */
    void updateRemind(Remind remind);
    
    Remind findRemind(Integer id);
    
    /**
     * 查询用户所对应的remind
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Remind> findUser2Reminds();
}
