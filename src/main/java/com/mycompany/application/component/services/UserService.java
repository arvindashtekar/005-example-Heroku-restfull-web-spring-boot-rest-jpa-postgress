package com.mycompany.application.component.services;

import java.util.List;

import com.mycompany.application.component.domain.User;

public interface UserService {

    User save(User user);

    List<User> getList();

}

