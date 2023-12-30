package com.phamthainguyen.shop.respository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamthainguyen.shop.entity.user.User;

public interface UserRespository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
