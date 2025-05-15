package com.upeu.msauth.repository;

import com.upeu.msauth.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthUserRepository extends JpaRepository<AuthUser,Integer> {
    Optional<com.upeu.msauth.entity.AuthUser> findByUserName(String username);
}
