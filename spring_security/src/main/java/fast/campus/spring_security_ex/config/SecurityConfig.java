package fast.campus.spring_security_ex.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.Collection;
import java.util.function.Supplier;

@Configurable
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        UserDetails danny = User.builder()
//                .username("danny.kim")
//                .password("12345")
//                .authorities("ROLE_ADMIN")
//                .build();

        UserDetails danny = User.withUsername("danny.kim")
                .password("password")
                .roles("ADMIN")
//                .authorities("READ")
                .build();

        UserDetails steve = User.withUsername("steve.kim")
                .password("23456")
                .roles("MANAGER")
                // .authorities("ROLE_MANAGER")
//                .authorities("WRITE")
                .build();

        manager.createUser(danny);
        manager.createUser(steve);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // NoOpPasswordEncoder는 비밀번호 평문을 그대로 비교 -> deprecated
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        r -> {
                            r.requestMatchers(HttpMethod.GET,"/api/v1/hello").hasRole("ADMIN");
                            r.requestMatchers(HttpMethod.GET,"/api/v1/hi").hasRole("MANAGER");
                        }
//                        r -> r.anyRequest().hasRole("ADMIN")// .hasAuthority("WRITE")// .hasAnyAuthority("READ", "WRITE") // READ, WRITE 권한이 있는 사용자만 허용
//                        r -> r.anyRequest().access(customAuthManager())
                )
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

//    private AuthorizationManager<RequestAuthorizationContext> customAuthManager() {
//        return new AuthorizationManager<RequestAuthorizationContext>() {
//            @Override
//            public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
//                Authentication auth = authentication.get();
//                Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
//                boolean granted = authorities.stream().anyMatch(each -> each.getAuthority().equals("READ"));
//                return new AuthorizationDecision(granted);
//            }
//        };
//    }
}
