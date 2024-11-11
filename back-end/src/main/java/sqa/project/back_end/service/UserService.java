package sqa.project.back_end.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.project.back_end.models.User;
import sqa.project.back_end.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        if (userRepository.existsByUserId(user.getUserId())) {
            throw new RuntimeException("Username is already taken");
        }
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User findByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public boolean authenticateUser(Long userId, String password) {
        User user = findByUserId(userId);
        return password.equals(user.getPassword());
    }

}
