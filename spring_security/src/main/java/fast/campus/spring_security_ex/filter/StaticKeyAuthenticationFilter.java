package fast.campus.spring_security_ex.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StaticKeyAuthenticationFilter implements Filter {
    @Value("${authentication.key}")
    private String authenticationKey;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authentication = request.getHeader("Authorization");

        if(authenticationKey.equals(authentication)) {
            filterChain.doFilter(request, response);
            return;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
