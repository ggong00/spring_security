package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class MenuDto {
    private Long id;
    private String name;
    private String url;
    private Long seq;
    private String ico;
    private Long depth;
    private List<MenuDto> children;
    private List<RoleDto> roles;

    public static MenuRes toMenuRes (MenuDto menu) {
        return new MenuRes(
                menu.getId(),
                menu.getName(),
                menu.getUrl(),
                menu.getSeq(),
                menu.getDepth(),
                menu.getIco(),
                Optional.ofNullable(menu.getChildren()).orElseGet(Collections::emptyList)
                        .stream().map(MenuDto::toMenuRes).collect(Collectors.toList())
        );
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class MenuRes{
        private Long id;
        private String name;
        private String url;
        private Long seq;
        private Long depth;
        private String ico;
        private List<MenuRes> children;

    }
}