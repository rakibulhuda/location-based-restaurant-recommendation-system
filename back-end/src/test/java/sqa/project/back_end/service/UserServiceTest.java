package sqa.project.back_end.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sqa.project.back_end.models.User;
import sqa.project.back_end.repository.UserRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        User user = new User(1L, "testUser", "test@example.com", "password");
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> users = userService.getAll();
        assertEquals(1, users.size());
        assertEquals("testUser", users.get(0).getUsername());
    }

    @Test
    void testGetAllWhenNoUsers() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> users = userService.getAll();
        assertTrue(users.isEmpty());
    }

    @Test
    void testRegisterUser() {
        User user = new User(1L, "testUser", "test@example.com", "password");
        when(userRepository.existsByUserName("testUser")).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        userService.registerUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRegisterUserThrowsException() {
        User user = new User(1L, "testUser", "test@example.com", "password");
        when(userRepository.existsByUserName("testUser")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(user));
        assertEquals("Username is already taken", exception.getMessage());
    }

    @Test
    void testFindByUserName() {
        User user = new User(1L, "testUser", "test@example.com", "password");
        when(userRepository.findByUserName("testUser")).thenReturn(user);

        User foundUser = userService.findByUserName("testUser");
        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    void testAuthenticateUser() {
        User user = new User(1L, "testUser", "test@example.com", "password");
        when(userRepository.findByUserName("testUser")).thenReturn(user);

        boolean isAuthenticated = userService.authenticateUser("testUser", "password");
        assertTrue(isAuthenticated);
    }

    @Test
    void testAuthenticateUserFails() {
        User user = new User(1L, "testUser", "test@example.com", "password");
        when(userRepository.findByUserName("testUser")).thenReturn(user);

        boolean isAuthenticated = userService.authenticateUser("testUser", "wrongPassword");
        assertFalse(isAuthenticated);
    }
}