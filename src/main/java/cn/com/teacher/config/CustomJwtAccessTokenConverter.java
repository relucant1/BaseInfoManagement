package cn.com.teacher.config;

import cn.com.teacher.entity.Authority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/10
 * email：liuxiaowen@teacher.com.cn
 */
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter
{
    private static CustomJwtAccessTokenConverter instance = new CustomJwtAccessTokenConverter();

    private static String publicKey = " -----BEGIN PUBLIC KEY-----\n" +
        "            MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNQZKqTlO/+2b4ZdhqGJzGBDlt\n" +
        "            b5PZmBz1ALN2YLvt341pH6i5mO1V9cX5Ty1LM70fKfnIoYUP4KCE33dPnC7LkUwE\n" +
        "            /myh1zM6m8cbL5cYFPyP099thbVxzJkjHWqywvQih/qOOjliomKbM9pxG8Z1dB26\n" +
        "            hL9dSAZuA8xExjlPmQIDAQAB\n" +
        "            -----END PUBLIC KEY-----";

    private CustomJwtAccessTokenConverter(){}

    public static CustomJwtAccessTokenConverter getInstance()
    {
        try
        {
            instance.setVerifierKey(publicKey);
            instance.afterPropertiesSet();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return instance;
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String accessToken)
    {
        DefaultOAuth2AccessToken result = getDefaultOAuth2AccessToken(accessToken);
        LinkedHashMap<String, Object> info = new LinkedHashMap(result.getAdditionalInformation());
        Set<Map.Entry<String, Object>> set = info.entrySet();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            info.get("user_name"), info.get("jti"), getAuthorities(info));
        return authentication;
    }

    private DefaultOAuth2AccessToken getDefaultOAuth2AccessToken(String accessToken)
    {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(getInstance());
        OAuth2AccessToken auth2AccessToken = jwtTokenStore.readAccessToken(accessToken);
        return new DefaultOAuth2AccessToken(auth2AccessToken);
    }

    /**
     * 半小时以内视为快过期
     * @param accessToken
     * @return
     */
    public boolean isExpired(String accessToken)
    {
        DefaultOAuth2AccessToken result = getDefaultOAuth2AccessToken(accessToken);
        return result.getExpiresIn() <= 30 * 60;
    }

    private List<Authority> getAuthorities(LinkedHashMap info)
    {
        List<Authority> authorities = new ArrayList<Authority>();
        if(null != info.get("authorities"))
        {
            List<String> authList = (List<String>)info.get("authorities");

            for (String auth : authList)
            {
                authorities.add(new Authority(auth));
            }
        }
        return authorities;
    }
}
