package com.ylb.base.reflect;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 1, 获取系统类加载器
 * 2,获取系统类加载器的加载路径
 * 3,获取系统类加载器的父类加载器：得到扩展类加载器
 * 4,获取扩展类加载器的加载路径
 * 5，获取系统类加载器的父类加载器：得到应用类加载器
 */
public class ClassLoaderPropTest {

    public static void main(String[] args) throws IOException {

        //1, 获取系统类加载器
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemLoader);

        //2,获取系统类加载器的加载路径
        Enumeration<URL> em1 = systemLoader.getResources("");
        while(em1.hasMoreElements())
        {
            System.out.println(em1.nextElement());
        }
        //3,获取系统类加载器的父类加载器：得到扩展类加载器
        ClassLoader extensionLader = systemLoader.getParent();
        System.out.println("扩展类加载器：" + extensionLader);
        //4,获取扩展类加载器的加载路径
        System.out.println("扩展类加载器的加载路径："
                + System.getProperty("java.ext.dirs"));
        //5，获取系统类加载器的父类加载器：得到应用类加载器
        System.out.println("扩展类加载器的parent: "
                + extensionLader.getParent());
    }
}
