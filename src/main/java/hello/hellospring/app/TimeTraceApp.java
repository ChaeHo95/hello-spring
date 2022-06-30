package hello.hellospring.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect // AOP는 @Aspect를 적어 줘야한다
public class TimeTraceApp {
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)") // 어디에 적용 시킬지
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toLongString());
        try {
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long times = finish - start;
            System.out.println("END: " + joinPoint.toLongString() + " " + times + "ms");
        }
    }
}
