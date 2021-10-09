package com.example.demo.services.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest
{
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private User user;

    @Mock
    private UserDAO userDAO;

    @Mock
    private SecurityService securityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(user.getPassword()).thenReturn("password");
        when(securityService.md5("password")).thenReturn("passwordMD5");
    }

    @Test
    public void should_set_md5_password_when_calling_assignPassword()
    {
        userService.assignPassword(user);
        verify(user).setPassword("passwordMD5");
    }

    @Test
    public void should_call_update_user_when_calling_assign_password()
    {
        userService.assignPassword(user);
        verify(userDAO).updateUser(user);
    }
}
