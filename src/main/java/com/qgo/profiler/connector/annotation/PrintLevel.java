package com.qgo.profiler.connector.annotation;
/**
 * 日志打印级别，用户可以通过传入的打印级别控制日志输出
 * 方法上的打印级别会覆盖类上的打印级别
 * 
 * @author van
 */
public enum PrintLevel {
	INFO,

	DEBUG,

	ERROR,

	WARN
}
