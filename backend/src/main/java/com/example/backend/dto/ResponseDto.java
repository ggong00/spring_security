package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class ResponseDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ResponseRes{
        private String timestamp;
        private String code;
        private String message;

    }
}