package sqa.project.back_end.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPassword;

    public User(){}

    public User(Long userId, String username, String email, String password) {
        this.userId = userId;
        this.userName = username;
        this.userEmail = email;
        this.userPassword = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return userEmail;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String password) {
        this.userPassword = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}