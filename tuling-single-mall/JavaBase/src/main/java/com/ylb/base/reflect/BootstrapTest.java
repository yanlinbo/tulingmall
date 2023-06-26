package com.ylb.base.reflect;

import sun.misc.Launcher;

import java.net.URL;

/**
 * 根类加载器都加载了些什么东西？
 */
public class BootstrapTest {
    public static void main(String[] args) {
        // 获取根类加载器所加载的全部URL数组
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        // 遍历、输出根类加载器加载的全部URL
        for (int i = 0; i < urls.length; i++)
        {
            System.out.println(urls[i].toExternalForm());
        }
    }
}
