package com.test.tyro.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.tyro.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
