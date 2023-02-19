package com.example.backend.config.security;

import com.example.backend.config.security.login.JsonUsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final LogoutSuccessHandler logoutSuccessHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .authorizeRequests(authorize -> authorize
                        .antMatchers(JsonUsernamePasswordAuthenticationFilter.SPRING_SECURITY_LOGIN_URL,
                                "/api/system/users/signup"
                                ).permitAll()
                        .antMatchers("/api/**").authenticated()
                        .anyRequest().access("@authorizationChecker.check(request, authentication)")
                )
                .logout(config -> config
                        .logoutUrl("/api/logout")
                        .logoutSuccessHandler(logoutSuccessHandler)
                )
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(customAuthenticationEntryPoint) // 인증이 되지않은 유저가 요청을 했을때 동작
                        .accessDeniedHandler(customAccessDeniedHandler) // 서버에 요청을 할 때 액세스가 가능한지 권한을 체크후 액세스 할 수 없는 요청을 했을시 동작
                );

        return http.build();
    }
}