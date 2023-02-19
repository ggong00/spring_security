package com.example.backend.domain.system.role;

import com.example.backend.domain.system.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
    Optional<Role> findById(Long roleId);

    Page<Role> findAll(Pageable pageable);
}