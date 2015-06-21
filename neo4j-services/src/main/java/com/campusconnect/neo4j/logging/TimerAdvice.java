package com.campusconnect.neo4j.logging;

import com.jamonapi.Monitor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class TimerAdvice {

    public Object doTiming(ProceedingJoinPoint pjp) throws Throwable {
        PerfLogHolder perfLogger = PerfLogHolder.getPerfLogHolder();
        String name = createInvocationTraceName(pjp);
        if (!perfLogger.isRunning()) {
            return pjp.proceed();
        } else {
            Monitor monitor = perfLogger.getFactory().start(name);
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                monitor.stop();
            }
        }
    }

    protected String createInvocationTraceName(ProceedingJoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer("");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class clazz = method.getDeclaringClass();
        sb.append(clazz.getSimpleName());
        sb.append('.').append(method.getName());
        return sb.toString();
    }

}
