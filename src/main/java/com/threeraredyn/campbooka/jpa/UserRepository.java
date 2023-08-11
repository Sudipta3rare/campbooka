package com.threeraredyn.campbooka.jpa;

import com.threeraredyn.campbooka.entity.Role;
import com.threeraredyn.campbooka.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String username);
    public boolean existsByEmail(String username);
    public List<User> findAllByRole(Role role);
}