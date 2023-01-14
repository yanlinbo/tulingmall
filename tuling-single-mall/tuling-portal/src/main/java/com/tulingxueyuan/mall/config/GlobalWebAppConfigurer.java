package com.tulingxueyuan.mall.config;

import com.tulingxueyuan.mall.common.util.JwtTokenUtil;
import com.tulingxueyuan.mall.intercepter.AuthInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 权限验证拦截器
 * 作用： 1，白名单用户直接通过  2，未登录的用户直接拒绝访问  3，登陆过的用户判断是否拥有资源权限
 */
@Configuration
public class GlobalWebAppConfigurer implements WebMvcConfigurer {

    /**
     * 该拦截器主要是为了权限验证
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
//    }

    /**
     * 用户据验证的拦截器
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "secure.ignored")
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }

    /**
     * jwt 工具类
     * @return
     */
    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }
}
