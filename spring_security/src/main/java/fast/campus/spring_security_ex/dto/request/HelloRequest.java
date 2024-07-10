package fast.campus.spring_security_ex.dto.request;

import fast.campus.spring_security_ex.annotation.CustomEncryption;

public record HelloRequest(
        String id,
        @CustomEncryption
        String password
) {

}
