# Spring Security

### 전역 메서드 보안
Spring Security에서는 전역 메서드 보안이 기본적으로 비활성화 상태로 제공
-> 활성화를 해줘야 함
1. 호출 권한 부여
    - 여러 이용 권리에 따라 누군가가 메소드를 호출할 수 있는가?
    - 메서드가 실행된 후 반환 값에 접근 할 수 있는가

2. 필터링
    - 메서드가 받는 매개변수를 사전 필터링
    - 메서드가 실행된 후 호출자가 사후 필터링

```java
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    
}
```
어노테이션을 사용하여 활성화