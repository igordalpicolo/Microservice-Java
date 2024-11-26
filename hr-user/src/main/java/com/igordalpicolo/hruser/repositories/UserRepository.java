package com.igordalpicolo.hruser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igordalpicolo.hruser.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
