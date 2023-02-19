package com.example.backend.domain.system.privilege;

import com.example.backend.domain.system.role.Role;
import com.example.backend.domain.system.role_privilege.RolePrivilege;
import com.example.backend.dto.PrivilegeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DIM_PRIVILEGE")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "privilege", fetch = FetchType.LAZY)
    private List<RolePrivilege> roles;

    public PrivilegeDto toPrivilegeDto(){
        return PrivilegeDto.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

}