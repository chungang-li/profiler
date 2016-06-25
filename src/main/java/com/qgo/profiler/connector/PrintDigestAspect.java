package com.qgo.profiler.connector;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.qgo.profiler.Profiler;
import com.qgo.profiler.connector.annotation.PrintLevel;
import com.qgo.profiler.core.helper.PrintDigestHelper;
import com.qgo.profiler.core.util.JoinPoinUtil;

/**
 * 基于PrintDigest注解的日志输出aop 与PrintDigestInterceptor互斥输出
 * 
 * @author van
 */
@Aspect
public class PrintDigestAspect {
	@Pointcut("@within(com.qgo.profiler.connector.annotation.PrintDigest)||@annotation(com.qgo.profiler.connector.annotation.PrintDigest)")
	public void pointcut() {
	}

	@Before(value = "pointcut()")
	public void beforeAdvice(JoinPoint jp) {
		try {
			Method method = JoinPoinUtil.getMethod(jp);
			String methodFullName = JoinPoinUtil.createMethodFullName(method);

			if (!PrintDigestHelper.isMethodNeedSkipPrint(method)) {

				Object[] args = PrintDigestHelper.getCanPrintArgs(method, jp.getArgs());

				PrintLevel printLevel = PrintDigestHelper.getPrintLevelIfNull(method, PrintLevel.INFO);

				Profiler.callMark(methodFullName, args, printLevel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
