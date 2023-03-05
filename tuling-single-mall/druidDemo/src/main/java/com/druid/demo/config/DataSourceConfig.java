package com.druid.demo.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {


    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        //设置 filters 属性值为 stat，开启 SQL 监控
        //同时开启 sql 监控(stat) 和防火墙(wall)，中间用逗号隔开。
        //开启防火墙能够防御 SQL 注入攻击
        druidDataSource.setFilters("stat,wall");
        return druidDataSource;
    }

    /**
     * 开启 Druid 数据源内置监控页面
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();
        //向容器中注入 StatViewServlet，并将其路径映射设置为 /druid/*
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(statViewServlet, "/druid/*");
        //配置监控页面访问的账号和密码（选配）
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        Map<String,String> map = new HashMap<>();
        map.put("loginUsername", "admin");
        map.put("loginPassword", "123456");
        map.put("allow", "");
        map.put("deny", "");
        servletRegistrationBean.setInitParameters(map);
        return servletRegistrationBean;
    }

    /**
     * 向容器中添加 WebStatFilter
     * 开启内置监控中的 Web-jdbc 关联监控的数据
     * @return
     */
    @Bean
    public FilterRegistrationBean druidWebStatFilter() {
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(webStatFilter);
        // 监控所有的访问
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        // 监控访问不包括以下路径   ,/druid/*
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico");
        return filterRegistrationBean;
    }
}
