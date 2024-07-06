package fast.campus.spring_security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 필드에 부여할 커스텀 어노테이션
@Retention(RetentionPolicy.RUNTIME) // 런타임 시점에 동작
public @interface CustomEncryption {

}
