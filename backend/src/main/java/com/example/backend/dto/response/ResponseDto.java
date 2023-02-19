package com.example.backend.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ResponseDto {

    @Getter
    @Setter
    public static class ResponseRes{
        private String timestamp;
        private String code;
        private String message;

        private Object data;

        public ResponseRes(ResponseMsg responseMsg) {
            this.timestamp = LocalDate.now().toString();
            this.code = responseMsg.getCode();
            this.message = responseMsg.getMessage();
        }

        public ResponseRes setData(Object data) {
            this.data = data;

            return this;
        }
    }
}