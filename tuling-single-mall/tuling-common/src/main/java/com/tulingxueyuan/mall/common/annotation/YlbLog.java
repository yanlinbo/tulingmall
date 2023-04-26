package com.tulingxueyuan.mall.common.annotation;

import com.tulingxueyuan.mall.common.enums.BusinessType;
import com.tulingxueyuan.mall.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * 通过切面来实现日志的记录
 *
 */
@Retention(RetentionPolicy.RUNTIME)  //注解生命周期为一直存在
@Target(ElementType.METHOD)  //注解只能用在方法上
@Documented  //将注解信息写在Java文档中
//@Inherited  如果一个使用了@Inherited 修饰的annotation 类型被用于一个class，则这个annotation 将被用于该class 的子类。
public @interface YlbLog {

    /**
     * module 表示记录的是那个模块的日志
     */
    public String module() default "";

    /**
     * 记录日志的类型
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 记录日志的操作对象
     */
    public OperatorType operatorType() default OperatorType.OTHER;

    /**
     * 日志属性后面有需要再扩展
     */


}
