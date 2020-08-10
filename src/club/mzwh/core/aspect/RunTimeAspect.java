package club.mzwh.core.aspect;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class RunTimeAspect {
	@Before("execution(* com.ce..dao..*.*(..))")
	public void doBefore(JoinPoint call) {
		Logger logger = Logger.getLogger(call.getTarget().getClass());
		long time1 = System.currentTimeMillis();
		long time2 = System.currentTimeMillis();
		long time = time2 - time1; // interval
		logger.info(time);
	}
	@After("execution(* com.ce..dao..*.*(..))")
	public void doAfter(JoinPoint call) {
		Logger logger = Logger.getLogger(call.getTarget().getClass());
		long time1 = System.currentTimeMillis();
		long time2 = System.currentTimeMillis();
		long time = time2 - time1; // interval
		logger.info(time);
	}

	@Around("execution(* com.ce..dao..*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) {
		Logger logger = Logger.getLogger(pjp.getTarget().getClass());
		Object retVal = null;
		try {
			StopWatch clock = new StopWatch();
			clock.start(); // 计时开始
			retVal = pjp.proceed();
			clock.stop();  //计时结束
			logger.info("Takes:" + clock.getTime() + " ms");
			logger.info(pjp.getTarget().getClass());
			logger.info(pjp.getSignature().getName());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return retVal;

	}
}
