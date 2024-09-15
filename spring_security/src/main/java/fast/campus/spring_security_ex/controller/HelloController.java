package fast.campus.spring_security_ex.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//    @GetMapping("/v1/hello") // REST 엔드포인트 생성
//    public String hello() {
//        return "Hello Spring Security";
//    }
    @GetMapping("/v1/hello")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }

    @GetMapping("/v1/hi")
    public String hi(Authentication authentication) {
        return "Hi" + authentication.getName();
    }
}
