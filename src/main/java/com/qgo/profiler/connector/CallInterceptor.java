package com.qgo.profiler.connector;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

import com.qgo.profiler.Profiler;

public class CallInterceptor extends AbstractMonitoringInterceptor {

	private static final long serialVersionUID = 7406883880062745237L;

	public CallInterceptor() {
	}

	public CallInterceptor(boolean useDynamicLogger) {
		setUseDynamicLogger(useDynamicLogger);
	}

	protected boolean isInterceptorEnabled(MethodInvocation invocation, Log logger) {
		return true;
	}

	@Override
	protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {

		String methodFullName = createInvocationTraceName(invocation);

		try {
			Profiler.callMark(methodFullName);

		} finally {
		}

		return invocation.proceed();
	}

}
