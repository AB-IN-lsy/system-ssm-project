package com.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
// ע��
@Aspect
// ����Ҳ�Ǹ��࣬����spring����
@Component
public class LogAspect {

	@Pointcut("execution(* com.controller.*.*(..))") // ����ס˭
	private void anyMethod() { // ���������
	}

	@Around("anyMethod()")
	public Object aroundMethod(ProceedingJoinPoint point) {
		Object result = null;

		log.trace("ǰ��֪ͨ������..�������¼�� trace��Ϣ");

		String methodName = point.getSignature().getName();
		log.info("��ǰִ�еķ�����:" + methodName);

		try {
			result = point.proceed(); // ����
			log.info("����ִ�����,������Ǻ���֪ͨ");
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("����ִ�г���,����֪ͨ");

		} finally {
			log.info("����֪ͨ������");
		}

		return result;
	}

}