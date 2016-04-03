package com.here2u.weixin.message.event;

/**
 * 上报地理位置事件
 * 
 * @author Joki
 * @version [V1.00, 2016年4月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class LocationEvent extends BaseEvent
{
    /**
     * 地理位置纬度
     */
    private String Latitude;
    
    /**
     * 地理位置经度
     */
    private String Longitude;
    
    /**
     * 地理位置精度
     */
    private String Precision;

    public String getLatitude()
    {
        return Latitude;
    }

    public void setLatitude(String latitude)
    {
        Latitude = latitude;
    }

    public String getLongitude()
    {
        return Longitude;
    }

    public void setLongitude(String longitude)
    {
        Longitude = longitude;
    }

    public String getPrecision()
    {
        return Precision;
    }

    public void setPrecision(String precision)
    {
        Precision = precision;
    }
}
