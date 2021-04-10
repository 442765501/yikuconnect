package com.yiku.aspect;

import com.alibaba.fastjson.JSON;
import com.yiku.common.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


/**
 * Created by 10853 on 2018/4/19.
 */
@Aspect
@Component
public class TimesAspect {


    /**监控所有对外接口的调用*/
    @Pointcut("@annotation(com.yiku.aspect.annotation.InterfaceCheck)")
    public void timesLog(){}


    @Before("timesLog()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        StringBuffer strBuff = new StringBuffer();
        strBuff.append(methodSignature.getReturnType()+"   ").append(methodSignature.getDeclaringTypeName()).append("."+methodSignature.getName()).append("("+JSON.toJSONString(joinPoint.getArgs())+")");
        LogUtil.info("API方法监控Before","{}",strBuff.toString());
    }
    @AfterReturning(returning="rvt" ,pointcut="timesLog()")
    public void after(JoinPoint joinPoint,Object rvt){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        StringBuffer strBuff = new StringBuffer();
        strBuff.append(methodSignature.getReturnType()+"   ").append(methodSignature.getDeclaringTypeName()).append("."+methodSignature.getName()).append("("+JSON.toJSONString(joinPoint.getArgs())+")");
        LogUtil.info("API方法监控After","{},返回值{}",strBuff.toString(),JSON.toJSONString(rvt));
    }
    @AfterThrowing(pointcut ="timesLog()",throwing="e")
    public void  afterThrowd(JoinPoint joinPoint,Exception e){
        LogUtil.info("API方法监控AfterThrowing" , "异常信息{}",JSON.toJSONString(e.getStackTrace()));
    }


}
