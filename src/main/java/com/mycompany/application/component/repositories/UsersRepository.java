package com.mycompany.application.component.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.application.component.domain.User;

public interface UsersRepository extends JpaRepository<User,Long> {
}
