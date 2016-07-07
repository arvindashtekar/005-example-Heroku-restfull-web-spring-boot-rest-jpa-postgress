package com.mycompany.application.component.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.application.component.domain.User;
import com.mycompany.application.component.repositories.UsersRepository;
import com.mycompany.application.component.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
	private UsersRepository usersRepository;

    @Override
    public User save(final User user) {
//TODO: Add validation
//        User existing = usersRepository.findOne(user.getId());
//        if (existing != null) {
//            throw new UserAlreadyExistsException(
//                    String.format("There already exists a user with id=%s", user.getId()));
//        }
        return usersRepository.save(user);
    }

    @Override
    public List<User> getList() {
        return usersRepository.findAll();
    }

}

