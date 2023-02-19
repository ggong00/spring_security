package com.example.backend.config.security;

import com.example.backend.dto.response.ResponseDto;
import com.example.backend.dto.response.ResponseMsg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;
    private final ResponseDto.ResponseRes response403;

    public CustomAuthenticationEntryPoint() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        this.objectMapper = builder.build();
        this.response403 = new ResponseDto.ResponseRes(ResponseMsg.UN_AUTHORIZED);
    }


    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(401);
        res.getWriter().write(objectMapper.writeValueAsString(this.response403));
    }
}