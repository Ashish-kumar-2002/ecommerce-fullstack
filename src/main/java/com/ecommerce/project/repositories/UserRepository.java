package com.ecommerce.project.repositories;
import com.ecommerce.project.model.AppRole;
import com.ecommerce.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByUserName(String username);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.roleName = :role")
    Page<User> findByRoleName(
            @Param("role") AppRole role,
            Pageable pageable
    );
}