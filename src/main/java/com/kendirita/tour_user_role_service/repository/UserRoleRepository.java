package com.kendirita.tour_user_role_service.repository;

import com.kendirita.tour_user_role_service.entity.User;
import com.kendirita.tour_user_role_service.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {

    boolean existsByEmail(String email);
    UserRole searchByEmail(String email);
    Optional<UserRole> findByEmail(String email);
}
