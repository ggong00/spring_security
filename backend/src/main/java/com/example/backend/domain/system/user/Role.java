package com.example.backend.domain.system.user;

import com.example.backend.domain.system.privilege.Privilege;
import com.example.backend.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();       //User에 정의된 roles의 객체가 list라서 맞춰봄

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "DIM_ROLE_PRIVILEGE",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private List<Privilege> privileges;

    public RoleDto toRoleDto(){
        return RoleDto.builder()
                .id(this.id)
                .name(this.name)
                .privileges(this.privileges.stream().map(Privilege::toPrivilegeDto).collect(Collectors.toList()))
                .build();
    }

    public RoleDto.RoleRes toRoleRes(){
        return RoleDto.RoleRes.builder()
                .id(this.id)
                .name(this.name)
                .privileges(this.privileges.stream().map(Privilege::toPrivilegeDto).collect(Collectors.toList()))
                .build();
    }
}