package com.druid.demo.controller;

import com.druid.demo.mapper.UmsAdminMapper;
import com.druid.demo.modle.UmsAdmin;
import com.druid.demo.service.UmsAdminService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/umsAdmin")
public class UmsAdminController {
    @Autowired
    UmsAdminService umsAdminService;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(value = "/batchInsertUmsAdmin", method = RequestMethod.GET)
    @ResponseBody
    public void batchInsertUmsAdmin() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("batchInsertUmsAdmin");
        UmsAdmin umsAdmin = null;
        for (Long i = 0L; i < 10000 ; i++) {
            umsAdmin = new UmsAdmin();
            umsAdmin.setCreateTime(new Date())
                    .setEmail("973644322@qq.com")
                    .setIcon("temp")
//                    .setId(i+1)
                    .setLoginTime(new Date())
                    .setNickName("yanlinbo")
                    .setNote("123")
                    .setPassword("123456")
                    .setStatus(1)
                    .setUsername("严林博");
            umsAdminService.insertUmsAdmin(umsAdmin);
        }
        stopWatch.stop();
        System.out.println("循环遍历插入10000条数据耗时："+stopWatch.getTotalTimeMillis());

    }

    @RequestMapping(value = "/batchInsertUmsAdmin01", method = RequestMethod.GET)
    @ResponseBody
    public void batchInsertUmsAdmin01() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("batchInsertUmsAdmin01");
        //具体实现
        SqlSession sqlSession=sqlSessionFactory.openSession(ExecutorType.BATCH);
        List<UmsAdmin> umsAdminList = new ArrayList<>();
        for (Long i = 0L; i < 10000 ; i++) {
            UmsAdmin umsAdmin = new UmsAdmin();
            umsAdmin.setCreateTime(new Date())
                    .setEmail("973644322@qq.com")
                    .setIcon("temp")
//                    .setId(i+100)
                    .setLoginTime(new Date())
                    .setNickName("yanlinboV1")
                    .setNote("123")
                    .setPassword("123456")
                    .setStatus(1)
                    .setUsername("严林博V1");
            umsAdminList.add(umsAdmin);
        }


        try{
            UmsAdminMapper mapper=sqlSession.getMapper(UmsAdminMapper.class);
            umsAdminList.stream().forEach(e->{
                mapper.batchInsertUmsAdmin(e);
            });
            sqlSession.clearCache();
            sqlSession.commit();
        }catch(Exception e){
            System.out.println(e);
        }finally{
            sqlSession.close();
        }
//        umsAdminService.batchInsertUmsAdmin(umsAdminList);
        stopWatch.stop();
        System.out.println("循环遍历插入10000条数据耗时："+stopWatch.getTotalTimeMillis());
    }

    @RequestMapping(value = "/batchInsertUmsAdmin02", method = RequestMethod.GET)
    @ResponseBody
    public void batchInsertUmsAdmin02() throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("batchInsertUmsAdmin02");
        int toindex = 100;
        //具体实现
        SqlSession sqlSession=sqlSessionFactory.openSession(ExecutorType.BATCH);
        List<UmsAdmin> umsAdminList = new ArrayList<>();
        Long time1 = System.currentTimeMillis();
        for (Long i = 0L; i < 10000 ; i++) {
            UmsAdmin umsAdmin = new UmsAdmin();
            umsAdmin.setCreateTime(new Date())
                    .setEmail("973644322@qq.com")
                    .setIcon("temp")
//                    .setId(i+100)
                    .setLoginTime(new Date())
                    .setNickName("yanlinboV1")
                    .setNote("123")
                    .setPassword("123456")
                    .setStatus(1)
                    .setUsername("严林博V1");
            umsAdminList.add(umsAdmin);
        }

        try{
            UmsAdminMapper mapper=sqlSession.getMapper(UmsAdminMapper.class);
            List<UmsAdmin> tempList = new ArrayList<>();
            for(int i=0;i<10000;i=i+100){
                if(i+100 < 10000){
                    toindex = 10000-i;
                }
                toindex = i+100;
                tempList = umsAdminList.subList(i,toindex);
                tempList.stream().forEach(e->{
                    mapper.batchInsertUmsAdmin(e);
                });
            }
            sqlSession.clearCache();
            sqlSession.commit();
        }catch(Exception e){
            System.out.println(e);
        }finally{
            sqlSession.close();
        }
        stopWatch.stop();
//        umsAdminService.batchInsertUmsAdmin(umsAdminList);
        System.out.println("循环分批次插入10000条数据耗时："+stopWatch.getTotalTimeMillis());

    }
}
