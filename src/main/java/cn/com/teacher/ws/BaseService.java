package cn.com.teacher.ws;

import org.springframework.transaction.TransactionStatus;

import java.util.Map;

/**
 * 服务模板类
 * @author lili 2017-05-26
 *
 */
public interface BaseService
{
    public Map<String, String> getParamMap();

    public String getParam(String key);

    public <T> T getObject(Class<T> clazz);

    public TransactionStatus getTxStatus();
}
