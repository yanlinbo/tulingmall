package com.tulingxueyuan.mall.common.util;

public class Convert {

    /**
     * 转换为字符串<br>
     * 如果给定的值为null，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     *
     * @param value 被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static String toStr(Object value, String defaultValue){

        if(null == value){ //如果value为null，则赋默认值defaultValue
            return defaultValue;
        }
        if(value instanceof String){ //如果value的类型为String，则强转String类型
            return (String)value;
        }
        return value.toString(); //如果value不为空，则改为String
    }
}
