package com.example.backend.config.security;

import com.example.backend.dto.response.ResponseDto;
import com.example.backend.dto.response.ResponseMsg;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;

    private final ResponseDto.ResponseRes responseLoginFail;


    public CustomAuthenticationFailureHandler() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        this.objectMapper = builder.build();
        this.responseLoginFail = new ResponseDto.ResponseRes(ResponseMsg.FAIL);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(200);
        res.getWriter().write(objectMapper.writeValueAsString(this.responseLoginFail));
    }

}