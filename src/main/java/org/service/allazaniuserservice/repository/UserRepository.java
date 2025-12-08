package org.service.allazaniuserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.service.allazaniuserservice.entity.User;
import org.service.allazaniuserservice.entity.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    List<User> findByFirstname(String firstname);
    List<User> findByLastname(String lastname);
    List<User> findByRole(Role role);
}
