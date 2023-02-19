package com.example.backend.domain.system.role_privilege;

import com.example.backend.domain.system.privilege.Privilege;
import com.example.backend.domain.system.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "DIM_ROLE_PRIVILEGE")
public class RolePrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Privilege privilege;

}

