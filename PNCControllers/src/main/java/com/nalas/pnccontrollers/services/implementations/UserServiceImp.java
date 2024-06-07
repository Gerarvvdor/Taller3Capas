package com.nalas.pnccontrollers.services.implementations;

import com.nalas.pnccontrollers.domain.dtos.UserRegiserDTO;
import com.nalas.pnccontrollers.domain.entities.Token;
import com.nalas.pnccontrollers.domain.entities.User;
import com.nalas.pnccontrollers.repositories.TokenRepository;
import com.nalas.pnccontrollers.repositories.UserRepository;
import com.nalas.pnccontrollers.services.UserService;
import com.nalas.pnccontrollers.utils.JWTTools;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    public PasswordEncoder passwordEncoder;

    private final JWTTools jwtTools;

    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository, JWTTools jwtTools, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.jwtTools = jwtTools;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User findByIdentifier(String identifier) {
        return userRepository.findByUsernameOrEmail(identifier, identifier).orElse(null);
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email).orElse(null);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void register(UserRegiserDTO info) {
        User user = new User();

        user.setUsername(info.getUsername());
        user.setEmail(info.getEmail());
        user.setPassword(passwordEncoder.encode(info.getPassword()));

        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(User user, String password) {

        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User findOneByIdentifier(String username) {
        try {
            Optional<User> userOptional = userRepository.findByUsername(username);
            return userOptional.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteFindOneByIdentifier(String identifier) {
        try {
            User user = userRepository.findByUsernameOrEmail(identifier, identifier).orElse(null);
            cleanTokens(user);

            if(user != null && user.getActive()) {
                user.setActive(false);

                userRepository.save(user);
                return true; // User deleted
            }else {
                return false; // User not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Error
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) throws Exception {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user);
        Token token = new Token(tokenString, user);
        tokenRepository.save(token);

        return token;
    }


    @Override
    public Boolean isTokenValid(User user, String token) {
        try {

            // Get all active tokens
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .filter(tk -> tk.getContent().equals(token))
                    .findAny()
                    .orElseThrow(() -> new Exception());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(User user) throws Exception {
        // Get all active tokens
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        // Verify if token is valid
        for (Token token : tokens) {
            token.setActive(false);
            tokenRepository.save(token);
        }

    }

    @Override
    public Optional<User> findUserAuthenticated() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsernameOrEmail(username, username);
    }
}
