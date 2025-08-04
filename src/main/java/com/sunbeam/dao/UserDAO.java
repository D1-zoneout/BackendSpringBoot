package com.sunbeam.dao;

import java.util.List;
import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Roles;
import com.sunbeam.entities.User;

public interface UserDao extends JpaRepository<User,Long>{
     Optional<User> findByEmail(String email);
//           Optional<User> findById(Long id);

	boolean existsByEmail(String email);

	List<User> findByUser(Roles user);
}
