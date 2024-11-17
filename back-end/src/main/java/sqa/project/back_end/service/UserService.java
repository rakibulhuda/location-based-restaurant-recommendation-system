package sqa.project.back_end.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.project.back_end.models.User;
import sqa.project.back_end.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void registerUser(User user) {
        if (userRepository.existsByUserName(user.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        userRepository.save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean authenticateUser(String userName, String password) {
        User user = findByUserName(userName);
        return password.equals(user.getPassword());
    }

}
