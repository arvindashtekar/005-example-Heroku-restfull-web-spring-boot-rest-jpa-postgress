package com.mycompany.application.component.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.application.component.domain.User;
import com.mycompany.application.component.services.UserService;
import com.mycompany.application.component.services.exceptions.UserAlreadyExistsException;

@RestController
@RequestMapping("/api/user")
public class UsersController {

	@Autowired
	private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody final User user) {
        return userService.save(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> listUsers() {
        return userService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return e.getMessage();
    }

}
