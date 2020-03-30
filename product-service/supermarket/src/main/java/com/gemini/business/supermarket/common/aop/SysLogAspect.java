package com.gemini.business.supermarket.common.aop;

import com.gemini.boot.framework.shiro.entity.UserInfo;
import com.gemini.boot.framework.shiro.utils.UserUtils;
import com.gemini.business.supermarket.common.annotation.SysLog;
import com.gemini.business.supermarket.common.utils.IPUtils;
import com.gemini.business.supermarket.platform.po.ErrorLogPo;
import com.gemini.business.supermarket.platform.po.OptLogPo;
import com.gemini.business.supermarket.platform.service.ErrorLogService;
import com.gemini.business.supermarket.platform.service.OptLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

//import com.gemini.boot.framework.web.com.gemini.business.oss.utils.IPUtils;
//import javax.servlet.http.HttpServletRequest;

/**
 * 操作日志记录
 *
 * @author 小明不读书
 * @date 2019-02-25
 */
@Aspect
@Component
public class SysLogAspect {
    final
    OptLogService optLogPoService;
    @Autowired
    ErrorLogService errorLogService;
    OptLogPo optLogPoPo = new OptLogPo();
    ErrorLogPo errorLogPo = new ErrorLogPo();

    public SysLogAspect(OptLogService optLogPoService) {
        this.optLogPoService = optLogPoService;
    }

    @Pointcut("@annotation(com.gemini.business.supermarket.common.annotation.SysLog)")
    public void sysLog() {

    }

    @Around("sysLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();

        //保存日志
        saveOptLog(point, beginTime);

        return result;
    }

    /**
     * 方法退出时执行
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "sysLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        optLogPoPo.setResult(null == ret ? "" : ret.toString());
        //保存操作日志
        optLogPoService.insertAsync(optLogPoPo, false);
    }

    /**
     * 后置异常通知
     *
     * @param jp
     */
    @AfterThrowing(throwing = "e", value = "sysLog()")
    public void afterThrowing(JoinPoint jp, Throwable e) {
        //保存日志
        saveExcpLog(jp);
        errorLogPo.setResult(e.toString());
//        errorLogService.insert(errorLogPo);
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     *
     * @param jp
     */
    @After("sysLog()")
    public void after(JoinPoint jp) {
    }

    private void saveOptLog(ProceedingJoinPoint joinPoint, long beginTime) {
        //用户名
        UserInfo user = UserUtils.getCurrentUser();
        if (user != null) {
            optLogPoPo.setUserAccount(user.getAccount());
            optLogPoPo.setUserName(user.getName());
        }
        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            //注解上的操作描述
            optLogPoPo.setDescription(sysLog.value());


//            Integer type = getType(request, sysLog);
            optLogPoPo.setOptTypeId(13123L);
            optLogPoPo.setOptTypeCode(request.getMethod());
            optLogPoPo.setOptTypeName("ok");
        }

        //请求地址
        optLogPoPo.setUrl(request.getRequestURI());

        //请求方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        optLogPoPo.setMethod(className + "." + methodName + "()");

        //请求参数
        optLogPoPo.setParams(Arrays.toString(joinPoint.getArgs()));

        //设置IP地址
        optLogPoPo.setIp(IPUtils.getIpAddr(request));

        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        optLogPoPo.setTime(time);

//        optLogPoPo.setCreateDatetime(new Date());
    }

    private void saveExcpLog(JoinPoint joinPoint) {


        //用户名
        UserInfo user = UserUtils.getCurrentUser();
        if (user != null) {
            optLogPoPo.setUserAccount(user.getAccount());
            optLogPoPo.setUserName(user.getName());
        }

        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            //注解上的操作描述
            errorLogPo.setDescription(sysLog.value());

//            Integer type = getType(request, sysLog);
//            excpLog.setType(type);
        }

        //请求地址
//        excpLog.setUrl(request.getRequestURI());

        //请求方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        errorLogPo.setMethod(className + "." + methodName + "()");

        //请求参数
        errorLogPo.setParams(Arrays.toString(joinPoint.getArgs()));

        //设置IP地址
//        excpLog.setIp(IPUtils.getIpAddr(request));

        errorLogPo.setCreateTime(new Date());
    }

    private Integer getType(HttpServletRequest request, SysLog sysLog) {
        Integer type = null;
        if ("GET".equals(request.getMethod())) {
            type = 1;
        } else if ("POST".equals(request.getMethod())) {
            if (sysLog.value().equals("用户登陆")) {
                type = 5;
            } else {
                type = 2;
            }
        } else if ("PUT".equals(request.getMethod())) {
            type = 3;
        } else if ("DELETE".equals(request.getMethod())) {
            type = 4;
        }
        return type;
    }
}
