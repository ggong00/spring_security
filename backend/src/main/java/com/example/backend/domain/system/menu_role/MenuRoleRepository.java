package com.example.backend.domain.system.menu_role;

import com.example.backend.domain.system.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRoleRepository extends JpaRepository<MenuRole, String> {

}