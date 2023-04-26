package com.tulingxueyuan.mall.common.aspect;

import com.tulingxueyuan.mall.common.annotation.YlbDataScope;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 通过切面实现数据权限
 */
@Aspect
@Component
public class YlbDataScopeAspect {
    private static final Logger log = LoggerFactory.getLogger(YlbDataScopeAspect.class);

    @Before("@annotation(ylbDataScope)")
    public void doBefore(JoinPoint point, YlbDataScope ylbDataScope) throws Throwable
    {
//        clearDataScope(point);
//        handleDataScope(point, controllerDataScope);
    }
}
