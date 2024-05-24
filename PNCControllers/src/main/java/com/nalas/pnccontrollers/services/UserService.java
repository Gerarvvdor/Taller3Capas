package com.nalas.pnccontrollers.services;

import com.nalas.pnccontrollers.domain.dtos.UserRegiserDTO;
import com.nalas.pnccontrollers.domain.entities.Token;
import com.nalas.pnccontrollers.domain.entities.User;

import java.util.Optional;

public interface UserService {
    User findByIdentifier(String identifier);

    User findByUsernameOrEmail(String username, String email);

    void register(UserRegiserDTO info);

    boolean checkPassword(User user, String password);

    User findOneByIdentifier(String username);

    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;
    Optional<User> findUserAuthenticated();
}