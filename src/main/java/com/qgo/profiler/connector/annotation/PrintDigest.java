package com.qgo.profiler.connector.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 注解为精细化打印 类/方法注解为精细化打印之后，
 * 通过{@link com.qgo.profiler.connector.PrintDigestInterceptor}配置的自动代理将会对其无效
 * 取而代之是通过用户自定义的打印设置进行打印
 * </pre>
 * 
 * @author van
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PrintDigest {
	PrintLevel printLevel() default PrintLevel.INFO;
}
