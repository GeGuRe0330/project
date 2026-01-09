package com.eunoia.config;

import java.util.List;

import com.eunoia.security.CustomAuthFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final CustomAuthFailureHandler customAuthFailureHandler;

    SecurityConfig(CustomAuthFailureHandler customAuthFailureHandler) {
        this.customAuthFailureHandler = customAuthFailureHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 회원가입 접근권한 허용 & 일시적 API 허용
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/members/**").permitAll()
                        .requestMatchers("/api/members/user/me").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/emotion-entries/**").authenticated()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
                .exceptionHandling(ex -> ex
                        // ✅ API는 로그인페이지로 보내지 말고 401을 반환
                        .defaultAuthenticationEntryPointFor(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                                request -> request.getRequestURI().startsWith("/api/")))
                // 기본 로그인 & 로그아웃 페이지
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .successHandler((req, res, auth) -> {
                            res.setStatus(200);
                            res.setContentType("application/json;charset=UTF-8");
                            res.getWriter().write("{\"ok\":true}");
                        })
                        .failureHandler(customAuthFailureHandler))
                .logout(Customizer.withDefaults());
        return http.build();
    }

    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    // CorsConfiguration config = new CorsConfiguration();
    // config.setAllowedOrigins(List.of("http://localhost:5173"));
    // config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH",
    // "OPTIONS"));
    // config.setAllowedHeaders(List.of("*"));
    // config.setAllowCredentials(true); // 세션 쿠키 허용
    // config.setExposedHeaders(List.of("Set-Cookie"));

    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // source.registerCorsConfiguration("/**", config);
    // return source;
    // }
}
