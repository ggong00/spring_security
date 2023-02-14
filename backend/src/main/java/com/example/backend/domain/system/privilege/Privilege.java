package com.example.backend.domain.system.privilege;

import com.example.backend.domain.system.user.Role;
import com.example.backend.dto.PrivilegeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

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

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
    private Collection<Role> roles;

    public PrivilegeDto toPrivilegeDto(){
        return PrivilegeDto.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

}