package com.qgo.profiler.connector;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.qgo.profiler.Profiler;
import com.qgo.profiler.core.helper.PrintProfilerHelper;
import com.qgo.profiler.core.util.JoinPoinUtil;

@Aspect
public class PrintProfilerAspect {
	@Pointcut("@within(com.qgo.profiler.connector.annotation.PrintProfiler)||@annotation(com.qgo.profiler.connector.annotation.PrintProfiler)")
	public void pointcut() {
	}

	@Around("com.qgo.profiler.connector.PrintProfilerAspect.pointcut()")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		String methodFullName = "unkonwMethod()";
		int monitorTime = 500;
		try {
			Method method = JoinPoinUtil.getMethod(pjp);
			methodFullName = JoinPoinUtil.createMethodFullName(method);
			monitorTime = PrintProfilerHelper.getElapseTimeIfNull(method, monitorTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Profiler.start(methodFullName, monitorTime);
			return pjp.proceed();
		} finally {
			Profiler.stop();
		}
	}
}
