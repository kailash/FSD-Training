package com.stackroute.usertrackservice.config;

import com.stackroute.rabbitmq.domain.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

  private RabbitTemplate rabbitTemplate;
  private DirectExchange directExchange;

  @Autowired
  public Producer(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
    this.rabbitTemplate = rabbitTemplate;
    this.directExchange = directExchange;
  }

  public void sendMessagetoRabbitMq(UserDTO userDTO){
    rabbitTemplate.convertAndSend(directExchange.getName(),"user_routing",userDTO);

  }

  public void send2RabbitMqTrackObject(UserDTO userDTO){
    rabbitTemplate.convertAndSend(directExchange.getName(),"track_routing",userDTO);
  }
}
