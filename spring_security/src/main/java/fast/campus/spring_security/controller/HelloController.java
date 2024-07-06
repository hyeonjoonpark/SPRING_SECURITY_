package fast.campus.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/v1/hello") // REST 엔드포인트 생성
    public String hello() {
        return "Hello Spring Security";
    }
}
