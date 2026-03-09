package com.mini.project.repo;

import com.mini.project.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    // Mantra sakti untuk mencari prajurit berdasarkan emailnya
    Optional<UserEntity> findByEmail(String email);
    
}