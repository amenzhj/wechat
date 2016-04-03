package com.here2u.weixin.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 信任管理器
 * 
 * @author Joki
 * @version [V1.00, 2016年4月3日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class MyX509TrustManager implements X509TrustManager
{
    
    /**
     * 检查客户端证书
     * 重写方法
     * 
     * @param chain
     * @param authType
     * @throws CertificateException
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], java.lang.String)
     */
    public void checkClientTrusted(X509Certificate[] chain, String authType)
        throws CertificateException
    {
    }
    
    /**
     * 检查服务器端证书
     * 重写方法
     * 
     * @param chain
     * @param authType
     * @throws CertificateException
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
     */
    public void checkServerTrusted(X509Certificate[] chain, String authType)
        throws CertificateException
    {
    }
    
    /**
     * 返回受信任的X509证书数组
     * 重写方法
     * 
     * @return
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    public X509Certificate[] getAcceptedIssuers()
    {
        return null;
    }
}
