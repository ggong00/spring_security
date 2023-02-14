package com.example.backend.domain.menu;

import com.example.backend.domain.CommonEntity;
import com.example.backend.domain.system.user.Role;
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

    @Column(name = "depth")
    private Long depth;

    @Column(name = "seq")
    private Long seq;

    @Column(name = "ico")
    private String ico;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Menu> children = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "DIM_MENU_ROLE",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    public MenuDto toMenuDto(){
        return MenuDto.builder()
                .id(this.id)
                .name(this.name)
                .url(this.url)
                .seq(this.seq)
                .ico(this.ico)
                .depth(this.depth)
                .children(this.children.stream().map(Menu::toMenuDto).collect(Collectors.toList()))
                .roles(this.roles.stream().map(Role::toRoleDto).collect(Collectors.toList()))
                .build();
    }
}
