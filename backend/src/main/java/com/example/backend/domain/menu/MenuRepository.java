package com.example.backend.domain.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {

    Optional<Menu> findByUrl(String url);

    List<Menu> findByParentIsNull();
}