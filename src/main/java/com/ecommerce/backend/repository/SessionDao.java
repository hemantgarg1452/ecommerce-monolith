package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionDao extends JpaRepository<UserSession, Integer> {

    Optional<UserSession> findByToken(String token);
    Optional<UserSession> findByUserId(Integer userId);
}
