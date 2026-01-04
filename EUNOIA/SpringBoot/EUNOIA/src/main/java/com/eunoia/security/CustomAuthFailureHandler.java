package com.eunoia.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");

        // 1) 승인 대기/비활성 계정
        if (exception instanceof DisabledException) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
            objectMapper.writeValue(response.getWriter(), Map.of(
                    "code", "ACCOUNT_NOT_ACTIVE",
                    "message", "아직 승인 대기중인 계정입니다. 나중에 다시 시도해주세요."));
            return;
        }

        // 2) 이메일 없음 or 비밀번호 틀림
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            objectMapper.writeValue(response.getWriter(), Map.of(
                    "code", "BAD_CREDENTIALS",
                    "message", "아이디 또는 비밀번호가 일치하지 않습니다."));
            return;
        }

        // 3) 기타 예외
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        objectMapper.writeValue(response.getWriter(), Map.of(
                "code", "AUTH_FAILED",
                "message", "로그인에 실패했어요. 잠시 후 다시 시도해주세요.(서버)"));
    }
}
