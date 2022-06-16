package com.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
// 注解
@Aspect
// 切面也是个类，交给spring管理
@Component
public class LogAspect {

	@Pointcut("execution(* com.controller.*.*(..))") // 想拦住谁
	private void anyMethod() { // 定义切入点
	}

	@Around("anyMethod()")
	public Object aroundMethod(ProceedingJoinPoint point) {
		Object result = null;

		log.trace("前置通知触发了..在这里记录了 trace信息");

		String methodName = point.getSignature().getName();
		log.info("当前执行的方法是:" + methodName);

		try {
			result = point.proceed(); // 放行
			log.info("方法执行完成,输出的是后置通知");
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("方法执行出错,例外通知");

		} finally {
			log.info("最终通知触发了");
		}

		return result;
	}

}