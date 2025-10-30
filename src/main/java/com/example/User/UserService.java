package com.example.User;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.
                findByUsernameAndPassword(
                        loginRequest.getUsername(),
                        loginRequest.getPassword());
        return user.isPresent();
    }

    public Boolean register(User user) {
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
