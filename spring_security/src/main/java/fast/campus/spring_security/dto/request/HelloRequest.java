package fast.campus.spring_security.dto.request;

import fast.campus.spring_security.annotation.CustomEncryption;

public record HelloRequest(
        String id,
        @CustomEncryption
        String password
) {

}
