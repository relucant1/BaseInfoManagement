package cn.com.teacher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/6/2
 * email：liuxiaowen@teacher.com.cn
 */
@Configuration
public class FreemarkerConfig
{
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer()
    {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        Properties settings = new Properties();
        settings.put("number_format","0");
        settings.put("template_update_delay","0");
        settings.put("locale","zh_CN");
        settings.put("template_exception_handler","ignore");
        settings.put("whitespace_stripping","true");
        settings.put("auto_import","/sectionSubject/elearning_macros.ftl as tp");
        freeMarkerConfigurer.setFreemarkerSettings(settings);
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
        freeMarkerConfigurer.setDefaultEncoding("utf-8");
        return freeMarkerConfigurer;
    }

    @Bean
    public FreeMarkerViewResolver viewResolver()
    {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setViewClass(FreeMarkerView.class);
        viewResolver.setCache(false);
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setRequestContextAttribute("request");
        viewResolver.setExposeRequestAttributes(true);
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setAllowRequestOverride(true);
        viewResolver.setAllowSessionOverride(true);
        return viewResolver;
    }
}
