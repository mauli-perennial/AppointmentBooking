package com.appointemnt.perennial.dao;

import com.appointemnt.perennial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    <S extends User> S save(S entity);
   User findUserById(Long id);
}