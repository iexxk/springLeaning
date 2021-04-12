package com.exxk.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.logging.Logger;

@Aspect
@Component
public class LogAspect {
    private Logger logger=Logger.getLogger(String.valueOf(getClass()));

    //定义切入点
    //@Pointcut("execution(public * com.exxk.aop..*Controller.*(..))")
    //@Pointcut("execution(public * com.exxk.aop.AopController.aop())")
    @Pointcut("@annotation(com.exxk.aop.Log)||@annotation(com.exxk.aop.SimpleLog)")
    public void pointCut(){}

    //切入点前插入的内容
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("切入点前执行的内容:");
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request=attributes.getRequest();
            logger.info("URL:"+request.getRequestURL().toString());
        }
        //获取注解
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Log log= method.getAnnotation(Log.class);
        logger.info("获取注解内容："+log);
    }

    @AfterReturning(returning = "ret",pointcut = "pointCut()")
    public void doAfterReturning(Object ret){
        logger.info("切点运行完之后执行的内容:");
        logger.info("response返回:"+ret);
    }
}
