package com.here2u.weixin.pojo;

/**
 * 
 * 凭证
 * 
 * @author Joki
 * @version [V1.00, 2016年3月31日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class Token
{
    /**
     * 接口访问凭证
     */
    private String accessToken;
    
    /**
     * 凭证有效期，单位：秒
     */
    private int expiresIn;

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public int getExpiresIn()
    {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn)
    {
        this.expiresIn = expiresIn;
    }
    
    @Override
    public String toString()
    {
        return "Token [accessToken=" + accessToken + ", expiresIn=" + expiresIn + "]";
    }

}