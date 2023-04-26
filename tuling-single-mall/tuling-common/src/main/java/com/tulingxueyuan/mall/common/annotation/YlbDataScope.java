package com.tulingxueyuan.mall.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)  //注解生命周期为一直存在
@Target(ElementType.METHOD)  //注解只能用在方法上
@Documented  //将注解信息写在Java文档中
public @interface YlbDataScope {
    /**
     * 部门表的别名
     */
    public String deptAlias() default "";

    /**
     * 用户表的别名
     */
    public String userAlias() default "";

    /**
     * 权限字符（用于多个角色匹配符合要求的权限）默认根据权限注解@RequiresPermissions获取，多个权限用逗号分隔开来
     */
    public String permission() default "";
}
