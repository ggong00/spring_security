package com.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(
        value = "/api/main",
        produces = "application/json"
)
public class RootController {

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('MAIN_READ')")
    ResponseEntity<?> getRead(){

        return new ResponseEntity<>("MAIN_READ", HttpStatus.OK);
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('MAIN_WRITE')")
    ResponseEntity<?> getWrite(){

        return new ResponseEntity<>("MAIN_WRITE", HttpStatus.OK);
    }
}