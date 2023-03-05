package com.druid.demo.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebServlet;


/**
 * 不推荐这种方式
 *     StatViewServlet 是一个标准的 javax.servlet.http.HttpServlet，想要开启 Druid 的内置监控页面，
 *     需要将该 Servlet 配置在 Web 应用中的 WEB-INF/web.xml 中
 *   但Spring Boot 项目中是没有 WEB-INF/web.xml 的，因此我们可以 自定义一个类继承StatViewServlet，在自定义的类上使用注解，
 *     且在启动类上使用@ServletComponentScan。（不推荐）
 */
@WebServlet(name = "myDruidStatView", urlPatterns = "/durid/*")
public class MyDruidStatView extends StatViewServlet {
}
