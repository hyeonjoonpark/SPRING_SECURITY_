package fast.campus.spring_security_ex.service;

import org.springframework.stereotype.Service;

@Service
public class EncryptService {
    public String encrypt(String before) {
        return "encrypt_" + before;
    }
}
