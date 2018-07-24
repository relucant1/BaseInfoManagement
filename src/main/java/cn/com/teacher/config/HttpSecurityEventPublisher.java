package cn.com.teacher.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/6/12
 * email：liuxiaowen@teacher.com.cn
 */
@Component
public class HttpSecurityEventPublisher implements ApplicationEventPublisherAware
{
    private ApplicationEventPublisher publisher;

    public void setApplicationEventPublisher(ApplicationEventPublisher publisher)
    {
        this.publisher = publisher;
    }

    public void publish(HttpSecurityConfigEvent event)
    {
        publisher.publishEvent(event);
    }
}
