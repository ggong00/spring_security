package com.example.backend.domain.system.menu;

import com.example.backend.domain.CommonEntity;
import com.example.backend.domain.system.menu_role.MenuRole;
import com.example.backend.dto.MenuDto;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter // do not create/use `setter` on Entity class
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
@Table(name = "DIM_MENU")
public class Menu extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String url;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Menu parent;

    @Column(name = "group")
    private Long group;

    @Column(name = "depth")
    private Long depth;

    @Column(name = "seq")
    private Long seq;

    @Column(name = "ico")
    private String ico;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Menu> children = new ArrayList<>();

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<MenuRole> roles = new ArrayList<>();

    public MenuDto toMenuDto(){

        return MenuDto.builder()
                .id(this.id)
                .name(this.name)
                .url(this.url)
                .seq(this.seq)
                .ico(this.ico)
                .depth(this.depth)
                .group(this.group)
                .children(this.children.stream().map(Menu::toMenuDto).collect(Collectors.toList()))
                .roles(this.roles.stream().map(menuRole -> menuRole.getRole().toRoleDto()).collect(Collectors.toList()))
                .build();
    }
}
