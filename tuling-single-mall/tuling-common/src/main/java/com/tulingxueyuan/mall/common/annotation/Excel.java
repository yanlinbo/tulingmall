package com.tulingxueyuan.mall.common.annotation;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义导出Excel数据注解
 *
 * @author ruoyi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    /**
     * 导出到Excel中的名字.
     */
    public String name() default "";

    /**
     * 另一个类中的属性名称,支持多级获取,以小数点隔开
     */
    public String targetAttr() default "";
    /**
     * 导出单元格字体颜色
     */
    public IndexedColors color() default IndexedColors.BLACK;
    /**
     * 导出单元格背景色
     */
    public IndexedColors backgroundColor() default IndexedColors.WHITE;

    /**
     * 导出字段对齐方式
     */
    public HorizontalAlignment align() default HorizontalAlignment.CENTER;

    /**
     * 导出列头背景色
     */
    public IndexedColors headerBackgroundColor() default IndexedColors.GREY_50_PERCENT;
    /**
     * 导出列头字体颜色
     */
    public IndexedColors headerColor() default IndexedColors.WHITE;
    /**
     * 导出时在excel中排序
     */
    public int sort() default Integer.MAX_VALUE;

    /**
     * 导出时在excel中每个列的高度 单位为字符
     */
    public double height() default 14;


    /**
     * 字段类型（0：导出导入；1：仅导出；2：仅导入）
     */
    Type type() default Type.ALL;

    public enum Type
    {
        ALL(0), EXPORT(1), IMPORT(2);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }
}
