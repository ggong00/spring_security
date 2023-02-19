package com.example.backend.dto;

import com.example.backend.domain.system.role.Role;
import com.example.backend.domain.system.user.User;
import lombok.*;

import java.util.List;

@Data
public class UserDto {

    /**
     * User 검색 시 사용되는 class
     */
    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserReq{
        private String userId;

        public User toEntity(){
            return User.builder()
                    .userId(this.userId)
                    .build();
        }
    }

    /*
     *  회원가입
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SignUpReq{
        private String userId;
        private String password;
        private String passwordConfirm;
        private Role roles;
        private String userNm;
        private String deptCd;
        private String rankCd;
        private String cellNo;
        private String email;
        private Integer authCd;
        private String useYn;
        private String admYn;
        private String joinDt;
        private String comment;

    }

    /*
     * 로그인
     */
    @Data
    @AllArgsConstructor
    @Builder
    public static class SignInReq{
        private String userId;
        private String password;

        public User toEntity(){
            return User.builder()
                    .userId(this.userId)
                    .password(this.password)
                    .build();
        }
    }

    /*
     * User 업데이트
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UpdateUserReq{
//        일반 사용자/관리자에 따라 입력값이 다를 수 있음.
        private String userId;
        private String passwordNew;
        private String passwordNewConfirm;
        private String userNm;
        private String email;
        private String cellNo;
        private Role roles;
        private String useYn;
        private String comment;
        private String admYn;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class QuitReq{
        private String userId;
        private String password;
        private String passwordConfirm;
    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class UserRes{
        private String userId;
        private String userNm;
        private String cellNo;
        private String email;
        private String joinDt;
        private String useYn;
        private String admYn;
        private String comment;
        private List<String> roles;

    }


}
