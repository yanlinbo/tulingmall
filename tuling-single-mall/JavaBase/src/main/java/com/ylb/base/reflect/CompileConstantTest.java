package com.ylb.base.reflect;

class MyTest
{
    static
    {
        System.out.println("静态初始化块...");
    }
    // 使用一个字符串直接量为static final的类变量赋值
    static final String compileConstant = "疯狂Java讲义";
}

public class CompileConstantTest {
    public static void main(String[] args)
    {
        // 访问、输出MyTest中的compileConstant类变量
        System.out.println(MyTest.compileConstant);
    }
}
