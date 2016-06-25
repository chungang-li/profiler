package com.qgo.profiler.connector;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

import com.qgo.profiler.Profiler;
import com.qgo.profiler.core.helper.PrintProfilerHelper;

public class ProfilerInterceptor extends AbstractMonitoringInterceptor {

	private static final long serialVersionUID = 7406883880062745237L;

	private int monitorTime;

	public ProfilerInterceptor() {
	}

	public ProfilerInterceptor(boolean useDynamicLogger) {
		setUseDynamicLogger(useDynamicLogger);
	}

	protected boolean isInterceptorEnabled(MethodInvocation invocation, Log logger) {
		return true;
	}

	@Override
	protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {
		String name = createInvocationTraceName(invocation);
		//是否委托了注解的拦截
		boolean isExistsPrintProfiler = PrintProfilerHelper.isMethodExistsPrintProfiler(invocation.getMethod());
		try {
			if (!isExistsPrintProfiler) {
				Profiler.start(name, monitorTime);
			}
			return invocation.proceed();
		} finally {
			if (!isExistsPrintProfiler) {
				Profiler.stop();
			}
		}
	}

	public int getMonitorTime() {
		return monitorTime;
	}

	public void setMonitorTime(int monitorTime) {
		this.monitorTime = monitorTime;
	}

}
