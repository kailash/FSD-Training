package com.stackroute.authenticationservice.config;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.service.UserServiceImpl;
import com.stackroute.rabbitmq.domain.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  UserServiceImpl userService;

  @Autowired
  public Consumer(UserServiceImpl userService) {
    this.userService = userService;
  }

  @RabbitListener(queues="user_queue")
  public void getuserDTO(UserDTO userDTO){

    User user=new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(userDTO.getPassword());
    userService.saveUser(user);
  }

}
