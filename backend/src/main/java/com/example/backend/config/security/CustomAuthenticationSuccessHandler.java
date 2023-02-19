package com.example.backend.config.security;

import com.example.backend.dto.response.ResponseDto;
import com.example.backend.dto.response.ResponseMsg;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;
    private final ResponseDto.ResponseRes responseLoginSuccess;

    public CustomAuthenticationSuccessHandler() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        this.objectMapper = builder.build();
        this.responseLoginSuccess = new ResponseDto.ResponseRes(ResponseMsg.LOGIN_SUCCESS);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(200);
        res.getWriter().write(objectMapper.writeValueAsString(this.responseLoginSuccess));
    }
}