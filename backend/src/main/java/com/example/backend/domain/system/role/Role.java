package com.example.backend.domain.system.role;

import com.example.backend.domain.system.menu_role.MenuRole;
import com.example.backend.domain.system.privilege.Privilege;
import com.example.backend.domain.system.role_privilege.RolePrivilege;
import com.example.backend.domain.system.user.User;
import com.example.backend.dto.RoleDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter // do not create/use `setter` on Entity class
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DIM_ROLE")
public class Role {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @Column
    private String encodedNm;

    @Column
    private String comment;

    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<RolePrivilege> privileges;

    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<MenuRole> menus;
    public RoleDto toRoleDto(){
        return RoleDto.builder()
                .id(this.id)
                .name(this.name)
                .privileges(
                        this.privileges
                                .stream()
                                .map(rolePrivilege -> rolePrivilege.getPrivilege().toPrivilegeDto())
                                .collect(Collectors.toList()))
                .build();
    }

    public RoleDto.RoleRes toRoleRes(){
        return RoleDto.RoleRes.builder()
                .id(this.id)
                .name(this.name)
                .privileges(
                        this.privileges
                                .stream()
                                .map(rolePrivilege -> rolePrivilege.getPrivilege().toPrivilegeDto())
                                .collect(Collectors.toList()))
                .build();
    }
}