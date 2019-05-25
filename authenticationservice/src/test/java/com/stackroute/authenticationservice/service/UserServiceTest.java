package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private User user;

    @InjectMocks
    UserServiceImpl userService;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        user=new User();
        user.setUsername("john");
        user.setPassword("john123");
    }


    @Test
    public void saveUserSuccessTest(){
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User fetchedUser=userService.saveUser(user);
        Assert.assertEquals(user,fetchedUser);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void findByUsernameAndPasswordTest(){
        Mockito.when(userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword())).thenReturn(user);
        User fetchedUser=userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        Assert.assertEquals(user.getUsername(),fetchedUser.getUsername());
        verify(userRepository,times(1)).findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
