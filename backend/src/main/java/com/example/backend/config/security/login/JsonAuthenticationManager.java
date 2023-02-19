package com.example.backend.config.security.login;

import com.example.backend.config.security.PBKDF2Util;
import com.example.backend.domain.system.user.User;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JsonAuthenticationManager implements AuthenticationManager {

    private final UserService userService;
    private final PBKDF2Util pbkdf2Util;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.loadUserByUsername((String) authentication.getPrincipal());//username
        boolean validated = false;

        try {
            validated = pbkdf2Util.validatePassword((String)authentication.getCredentials(), user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new BadCredentialsException("Not Found User");
        } catch (InvalidKeySpecException e) {
            throw new BadCredentialsException("Not Found User");
        }

        if(!validated){
            throw new BadCredentialsException("비밀번호 불일치.");
        }

        return new UsernamePasswordAuthenticationToken(user
                , user.getPassword()
                , user.getAuthorities());
    }
}