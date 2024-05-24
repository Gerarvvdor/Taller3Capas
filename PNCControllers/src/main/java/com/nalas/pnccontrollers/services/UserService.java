package com.nalas.pnccontrollers.services;

import com.nalas.pnccontrollers.domain.dtos.UserRegiserDTO;
import com.nalas.pnccontrollers.domain.entities.User;
public interface UserService {
    User findByIdentifier(String identifier);

    User findByUsernameOrEmail(String username, String email);

    void register(UserRegiserDTO info);

    boolean checkPassword(User user, String password);
}