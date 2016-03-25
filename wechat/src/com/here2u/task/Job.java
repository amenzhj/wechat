package com.here2u.task;

import java.util.Date;

import com.here2u.util.DateUtil;

public class Job
{
    public void doJob()
    {
        System.out.println(DateUtil.format(new Date()));
    }
}

