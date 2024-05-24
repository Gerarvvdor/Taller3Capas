package com.nalas.pnccontrollers.services.implementations;

import com.nalas.pnccontrollers.domain.dtos.UserRegiserDTO;
import com.nalas.pnccontrollers.domain.entities.User;
import com.nalas.pnccontrollers.repositories.UserRepository;
import com.nalas.pnccontrollers.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        user.setPassword(info.getPassword());

        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}
