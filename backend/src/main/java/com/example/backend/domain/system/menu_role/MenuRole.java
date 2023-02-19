package com.example.backend.domain.system.menu_role;

import com.example.backend.domain.system.menu.Menu;
import com.example.backend.domain.system.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter // do not create/use `setter` on Entity class
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
@Table(name = "DIM_MENU_ROLE")
public class MenuRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
}
