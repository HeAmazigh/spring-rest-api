package com.amazigh.hettal.springusers.repository;

import com.amazigh.hettal.springusers.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
