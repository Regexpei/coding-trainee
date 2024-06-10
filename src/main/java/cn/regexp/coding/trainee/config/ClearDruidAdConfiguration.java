package cn.regexp.coding.trainee.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author Regexpei
 * @date 2024/6/10 21:33
 * @description 清除 Druid Monitor 页面中的广告图
 */
@Configuration
// 当为 WEB 应用时进行加载
@ConditionalOnWebApplication
// 在 DruidDataSourceAutoConfigure 完成加载之后进行加载
@AutoConfigureAfter(DruidDataSourceAutoConfigure.class)
// 存在该配置并且为 true 时生效
@ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true")
public class ClearDruidAdConfiguration {

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(DruidStatProperties properties) {
        // 获取 WEB 页面监控参数
        DruidStatProperties.StatViewServlet servlet = properties.getStatViewServlet();
        // 获取 common.js 请求路径
        String urlPattern = servlet.getUrlPattern() != null ? servlet.getUrlPattern() : "/druid/*";
        // /druid/js/common.js
        String commonJsPattern = urlPattern.replaceAll("\\*", "js/common.js");

        return getFilterRegistrationBean(commonJsPattern);
    }

    /*
        FilterRegistrationBean 用于在 Servlet 容器执行请求过程中过滤一些特定的请求，
        并对请求的请求内容和响应结果做一些处理，例如权限拦截验证、访问日志、响应格式化等。
     */
    private static FilterRegistrationBean<Filter> getFilterRegistrationBean(String urlPatterns) {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter((request, response, chain) -> {
            chain.doFilter(request, response);
            // 清除响应中基础缓冲区的内容
            response.resetBuffer();
            // 读取文件
            String text = Utils.readFromResource("support/http/resources/js/common.js");
            // 注释广告图脚本
            text = text.replace("this.buildFooter();", "// this.buildFooter();");
            // 将新的 JS 内容写到响应体中
            response.getWriter().write(text);
        });
        registrationBean.addUrlPatterns(urlPatterns);
        return registrationBean;
    }

}
