package com.nalas.pnccontrollers.repositories;

import com.nalas.pnccontrollers.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
