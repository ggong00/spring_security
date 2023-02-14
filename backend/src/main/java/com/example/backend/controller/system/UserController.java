package com.example.backend.controller.system;

import com.example.backend.dto.UserDto;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RequiredArgsConstructor
@RestController
@RequestMapping(
        value = "/api/system/user",
        produces = "application/json"
)
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    ResponseEntity<?> postSignUp(@Validated @RequestBody UserDto.SignUpReq req) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserDto.UserRes userRes = userService.createUser(req);

        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }
}