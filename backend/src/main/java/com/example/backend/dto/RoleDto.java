package com.example.backend.dto;

import com.example.backend.domain.system.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class RoleDto {
    private Long id;
    private String name;
    private List<PrivilegeDto> privileges;

    
    /*
     * role 검색
     */
    @Getter
    @AllArgsConstructor
    @Builder
    public static class RoleReq{
        private Long id;
        private String name;

        public Role toEntity(){
            return Role.builder()
                    .id(this.id)
                    .name(this.name)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class RoleRes{
        private Long id;
        private String name;
        private List<PrivilegeDto> privileges;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class InsertReq{
        private Long id;
        private String name;
    }
}