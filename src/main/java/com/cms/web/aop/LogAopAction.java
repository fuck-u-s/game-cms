package com.cms.web.aop;

import com.cms.model.sys.AdminUser;
import com.cms.model.sys.SysLog;
import com.cms.service.sys.LogService;
import com.cms.utils.IPUtils;
import com.cms.utils.Logger;
import com.cms.utils.ResultUtil;
import com.cms.web.bind.LogWrite;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 方法描述:日志AOP
 * <p>
 * author lzh
 * version v1.0
 * date 2016/7/9 13:34
 */
@Aspect
@Component
public class LogAopAction {

    @Autowired
    private LogService logService;

    //Controller层切点
    @Pointcut("@annotation(com.cms.web.bind.LogWrite)")
    public  void controllerAspect() {

    }

    //环绕通知方法
    @Around("controllerAspect()")
    public Object doWriteLog(ProceedingJoinPoint pjp) throws Throwable {
        System.err.println("拦截方法,进入日志记录");
        // 登陆ip
        String ip = "无法获取登录用户Ip";
        // 登陆用户对象
        AdminUser adminUser = null;
        // shiro中获取登陆ip
        try {
            ip = SecurityUtils.getSubject().getSession().getHost();
        } catch (Exception e) {}

        // shiro中获取登陆对象
        try {
            // 登录名
            adminUser = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {}

        // 拦截的实体类
        Object target = pjp.getTarget();

        // 拦截的方法名称
        String methodName = pjp.getSignature().getName();

        // 拦截的方法参数
        Object[] args = pjp.getArgs();

        // 拦截的放参数类型
        Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getMethod().getParameterTypes();

        Object object = null;

        //需要转换成Json的HashMap
        Map<String, Object> maps = new HashMap<String, Object>();
        // 获得被拦截的方法
        Method method = target.getClass().getMethod(methodName, parameterTypes);
        if (null != method) {
            // 判断是否包含自定义的注解
            if (method.isAnnotationPresent(LogWrite.class)) {
                // 获取自定义注解实体
                LogWrite logWrite = method.getAnnotation(LogWrite.class);
                //日志类实体类
                final SysLog log = new SysLog();
                log.setUser_id(adminUser == null ? 0 : adminUser.getId());
                log.setModule(logWrite.module());
                log.setMethods(method.getName());
                log.setIp(ip);
                log.setDescription(adminUser == null ? "无法获取登录用户信息":"");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                maps.put("操作时间", sdf.format(new Date()));
                try {
                    // 执行方法所消耗的时间
                    long start = System.currentTimeMillis();
                    object = pjp.proceed();// 执行该方法
                    long end = System.currentTimeMillis();
                    maps.put("状态", "成功");
                    log.setStatus(0);
                    maps.put("消耗时间:",end - start);
                    log.setUseTime(end-start);

                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.error(e.getMessage());
                    maps.put("状态", "失败");
                    log.setStatus(1);
                    System.out.println("异常啦："+e.getMessage());
                    log.setException(e.getMessage());
                }
                log.setJson(ResultUtil.toJSON(maps));
                //记录相关日志
                if (null != logService) {
                    try {
                        final String thisIP = ip;
                        // 由于地区获取为接口所以有延迟,再次使用异步方法
                        Thread t = new Thread(){
                            public void run(){
                                long ipstart = System.currentTimeMillis();
                                log.setAreaName(IPUtils.getIpInfo(thisIP));
                                System.out.println("地区获取结束时间:"+(System.currentTimeMillis() - ipstart));
                                if(1 == logService.insert(log)){
                                    System.err.println("日志记录成功");
                                }
                                else{
                                    System.err.println("日志记录失败");
                                }
                            }
                        };
                        t.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger.error(e);
                    }
                }
                else{
                    System.err.println("自动注入失败，日志未记录");
                }

            } else {
                // 没有包含该注解则不进行其他处理
                object = pjp.proceed();// 执行该方法
            }

        } else { // 不需要拦截，直接运行
            object = pjp.proceed(); // 执行该方法
        }
        return object;
    }
}
