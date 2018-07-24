package cn.com.teacher.ws.impl;

import cn.com.teacher.ws.BaseService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author lili 2017-05-26
 *
 */
@Transactional
@Service
public class BaseServiceImpl implements BaseService{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Override
    public Map<String, String> getParamMap(){

    	Map<String, String> params = new HashMap<String, String>();
		try {
			request.setCharacterEncoding("utf-8");
			
	        Enumeration<String> names = request.getParameterNames();
	        while (names.hasMoreElements()) {
	            String prop = names.nextElement();
	            String value;
			value = request.getParameter(prop).trim();
			 params.put(prop, value);
	        }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	           
		return params;
    }

    @Override
    public String getParam(String key)
    {
        String value = getParamMap().get(key);
        if(null == value)
        {
            throw new RuntimeException(key + "参数不能为空！");
        }
        return value;
    }

    @Override
    public <T> T getObject(Class<T> clazz)
    {
        return JSON.parseObject(JSON.toJSONString(getParamMap()), clazz);
    }

    @Override
    public TransactionStatus getTxStatus()
    {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        return status;
    }
}
