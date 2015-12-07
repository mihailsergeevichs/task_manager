package com.mihailsergeevichs.todo.manager.user.repository;

import com.mihailsergeevichs.todo.manager.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public List<User> findAll();

}
