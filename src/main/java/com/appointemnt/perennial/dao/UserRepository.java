package com.appointemnt.perennial.dao;

import com.appointemnt.perennial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    <S extends User> S save(S entity);

    User findByUserNameAndPassword(String userName, String password);

    User findByUserName(String username);

}