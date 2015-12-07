package com.mihailsergeevichs.todo.manager.user.service;

import com.mihailsergeevichs.todo.manager.user.dto.RegistrationForm;
import com.mihailsergeevichs.todo.manager.user.model.User;

public interface UserService {

    public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
}
