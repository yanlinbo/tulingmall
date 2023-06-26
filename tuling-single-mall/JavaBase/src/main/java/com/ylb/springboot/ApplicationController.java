package com.ylb.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1,通过@Value直接读取配置文件
 */
@RestController
public class ApplicationController {


    @Value("${dbsp-queue.tdmq.client.sourceList[0].sender.destTopic[0]}")
    private String destTopic;

    @GetMapping("/test")
    public void test(){
        System.out.println("dbsp-queue.tdmq.client.sourceList[0].sender.destTopic[0]:"+destTopic);
    }

}
