package fast.campus.spring_security_ex.aspect;

import fast.campus.spring_security_ex.annotation.CustomEncryption;
import fast.campus.spring_security_ex.service.EncryptService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class PasswordEncryptionAspect {
    private final EncryptService encryptService;

    @Around("execution(* fast.campus.spring_security_ex.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Arrays.stream(joinPoint.getArgs())
                .forEach(this::fieldEncryption);

        return joinPoint.proceed();
    }

    public void fieldEncryption(Object obj) {
        if(ObjectUtils.isEmpty(obj)) {
            return;
        }

        FieldUtils.getAllFieldsList(obj.getClass())
                .stream()
                .filter(filter -> !(Modifier.isFinal(filter.getModifiers()) && Modifier.isStatic(filter.getModifiers())))
                .forEach(field -> {
                    try {
                        boolean encryptionTarget = field.isAnnotationPresent(CustomEncryption.class);
                        if (!encryptionTarget) {
                            return;
                        }

                        Object encryptionField = FieldUtils.readField(field, obj, true);

                        if (!(encryptionField instanceof String)) {
                            return;
                        }

                        String encrypted = encryptService.encrypt((String) encryptionField);
                        FieldUtils.writeField(field, obj, encrypted);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
