package com.geekbang.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid配置类
 *
 * @author 杨正
 */
@Configuration
public class DruidConfig {

    /*
     * 注意：
     *  引入依赖为
     *      <dependency>
     *             <groupId>com.alibaba</groupId>
     *             <artifactId>druid</artifactId>
     *             <version>1.1.10</version>
     *      </dependency>
     *      时：需要将yml中的配置通过this配置类添加到DataSource中
     *  引入的依赖为：
     *      <dependency>
     *             <groupId>com.alibaba</groupId>
     *             <artifactId>druid-spring-boot-starter</artifactId>
     *             <version>1.1.10</version>
     *      </dependency>
     *      时：则spring会自动将参数配置进去
     *
     * 详情请参考：https://blog.csdn.net/wangmx1993328/article/details/81865153
     *
     * <p>
     * 将自定义的 Druid 数据源添加到容器中，不再让 Spring Boot 自动创建
     * 这样做的目的是：绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource 从而让它们生效
     * {@link ConfigurationProperties }中prefix = "spring.datasource" ：作用就是将 全局配置文件中 前缀为 spring.datasource
     * 的属性值注入到 {@link com.alibaba.druid.pool.DruidDataSource} 的同名参数中
     * </p>
     *
     * @return {@link DataSource}
     */
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }

    /**
     * <p>
     * 配置 Druid 监控 之  管理后台的 Servlet
     * 内置 Servler 容器时没有web.xml 文件，所以使用 Spring Boot 的注册 Servlet 方式
     * 请求地址：http://localhost:8080/springBucks/druid/login.html
     * </p>
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> startViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initMap = new HashMap<>(5);
        initMap.put("loginUsername", "admin");
        initMap.put("loginPassword", "123456");
        initMap.put("allow", "");
        bean.setInitParameters(initMap);
        return bean;
    }

    /**
     * <p>
     * 配置 Druid 监控 之  web 监控的 filter
     * WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
     * </p>
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        /* exclusions：设置哪些请求进行过滤排除掉，从而不进行统计 */
        Map<String, String> initParams = new HashMap<>(5);
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);

        /* "/*" 表示过滤所有请求 */
        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;
    }

}
