package fast.campus.spring_security.aspect;

import fast.campus.spring_security.service.EncryptService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PasswordEncryptionAspect {
    private final EncryptService encryptService;

    @Around("execution(* fast.campus.spring_security.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
