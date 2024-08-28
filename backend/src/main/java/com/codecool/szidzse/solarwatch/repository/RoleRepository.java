package com.codecool.szidzse.solarwatch.repository;

import com.codecool.szidzse.solarwatch.model.entity.Role;
import com.codecool.szidzse.solarwatch.model.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
