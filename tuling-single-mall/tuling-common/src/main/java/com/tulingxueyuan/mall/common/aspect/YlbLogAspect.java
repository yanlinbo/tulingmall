package com.tulingxueyuan.mall.common.aspect;

import com.tulingxueyuan.mall.common.annotation.YlbLog;
import com.tulingxueyuan.mall.common.domain.SysOperLog;
import com.tulingxueyuan.mall.common.enums.BusinessStatus;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

@Aspect //表示该类为切面类
@Component  //todo 切面类必须为bean吗？
public class YlbLogAspect {
    private static final Logger log = LoggerFactory.getLogger(YlbLogAspect.class);  //Lombok中的@self注解相当于该行代码

    /** 日志中需要排除敏感属性字段 */
    public static final String[] EXCLUDE_PROPERTIES ={"password", "oldPassword", "newPassword", "confirmPassword"};  //类成员变量（不可变）

    /** 计算日志操作消耗时间 */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");  //为什么要使用ThreadLocal？

//    @Autowired
//    private AsyncLogService asyncLogService;   //写日志不属于业务操作，因此需要开启新线程异步操作


    /**
     * 在方法执行之前执行
     * @param joinPoint
     * @param ylbog
     */
    //@Before("execution(public int lzj.com.spring.aop.ArithmeticCalculator.*(int, int))")
    @Before(value = "@annotation(ylbog)")  //todo value = "@annotation(ylbog)"表达什么含义
    public void doBefore(JoinPoint joinPoint, YlbLog ylbog){
        TIME_THREADLOCAL.set(System.currentTimeMillis());  //再执行该注解的切面逻辑之前把当前时间记录在map中<当前线程，时间点>
    }


    /**
     *在方法返回结果之后执行
     * @param joinPoint
     * @param ylbLog
     */
    @AfterReturning(value = "@annotation(ylbLog)", returning = "jsonResult")  //todo value = "@annotation(ylbog)"表达什么含义
    public void doAfterReturning(JoinPoint joinPoint, YlbLog ylbLog, Object jsonResult){
        handleLog(joinPoint, ylbLog, null, jsonResult);
    }

    /**
     *在方法抛出异常之后执行
     * @param joinPoint
     * @param ylbLog
     */
    @AfterThrowing(value = "@annotation(ylbLog)", throwing = "e")  //
    public void doAfterThrowing(JoinPoint joinPoint, YlbLog ylbLog, Exception e){
        handleLog(joinPoint, ylbLog, e, null);
    }

    /**
     *
     * @param joinPoint
     * @param ylbLog
     */
    @After(value = "@annotation(ylbLog)")  //
    public void doAfter(JoinPoint joinPoint, YlbLog ylbLog){

    }

    /**
     * 围绕着方法执行
     * @param joinPoint
     * @param ylbLog
     */
    @Around(value = "@annotation(ylbLog)")  //
    public void doAround(JoinPoint joinPoint, YlbLog ylbLog){

    }

    protected void handleLog(final JoinPoint joinPoint, YlbLog ylbLog, final Exception e, Object jsonResult){

        try {
            SysOperLog operLog = new SysOperLog();
            operLog.setStatus(BusinessStatus.SUCCESS.ordinal()); //Enum类提供了一个ordinal()方法，用来返回枚举对象的序数，比如本例中SPRING, SUMMER, AUTUMN, WINTER的序数就分别是0，1，2，3。在某些情况下，我们需要根据这个序数生成我们需要的枚举对象
            //todo 这里需要写一个获取本地 IP的方法
            operLog.setOperIp("ip");
            //todo 这里需要写一个获取请求地址的方法
            operLog.setOperUrl("operUrl");
            operLog.setOperName("OperName");

            if (e != null)
            {
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(e.getMessage().substring( 0, 2000));
            }
            // 设置方法名称
            operLog.setMethod("className + \".\" + methodName + \"()\"");
            // 设置请求方式
            operLog.setRequestMethod("ServletUtils.getRequest().getMethod()");
            // 处理设置注解上的参数
            //getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
            // 设置消耗时间
            operLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());

            // 保存数据库
//        asyncLogService.saveSysLog(operLog);
        } catch (Exception ex) {
            // 记录本地异常日志
            log.error("异常信息:{}", ex.getMessage());
            ex.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();  //ThreadLocal最后一定不能忘掉remove，防止内存溢出
        }

    }

}
