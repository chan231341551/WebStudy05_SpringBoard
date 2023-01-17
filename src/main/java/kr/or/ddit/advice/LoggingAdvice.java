package kr.or.ddit.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAdvice {
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	/*@Before("dummy()")
	public void before(JoinPoint joinPoint) {
		Object target = joinPoint.getTarget();
		String targetName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String targetMethodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		log.info("{}.{}({} 호출됨)",targetName,targetMethodName,args);
	}
	@AfterReturning(pointcut="dummy()",returning="retValue")
	public void afterReturing(JoinPoint joinPoint, Object retValue) {
		
		Object target = joinPoint.getTarget();
		String targetName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String targetMethodName = signature.getName();
		log.info("({}.{} 실행결과 --> {})",targetName,targetMethodName,retValue);
	}*/
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		String targetName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String targetMethodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		long start = System.currentTimeMillis();
		try {
			log.info("{}.{}({} 호출됨)",targetName,targetMethodName,args);
			Object retValue = joinPoint.proceed(args);
			log.info("{}.{} 실행결과 --> {}",targetName,targetMethodName,retValue);
			long end = System.currentTimeMillis();
			log.info("{}.{} 실행소요시간 {}ms",targetName,targetMethodName,(end-start));
			
			return retValue;
		} catch (Throwable e) {
			log.error("",e);
			throw e;
		}
	}
		
}
