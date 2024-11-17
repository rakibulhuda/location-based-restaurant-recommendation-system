package sqa.project.back_end.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("userEmail")
    private String userEmail;

    @JsonProperty("userPassword")
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

    @JsonProperty("userName")
    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    @JsonProperty("userEmail")
    public String getEmail() {
        return userEmail;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }

    @JsonProperty("userPassword")
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