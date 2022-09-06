package com.appointemnt.perennial.service;

import com.appointemnt.perennial.dao.UserRepository;
import com.appointemnt.perennial.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * This class is for business logic for user.
 * @author Mauli satav
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * This methos is used for persisting an user in database
     * @param user accept user as input parameter.
     * @return will retrn the message when user is successfully persisted in database
     */
    @Override
    public String registerUser(User user) {
        userRepository.save(user);
        return "user registration succssfull";
    }
}
