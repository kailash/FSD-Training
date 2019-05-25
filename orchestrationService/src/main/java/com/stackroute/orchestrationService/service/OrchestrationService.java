package com.stackroute.orchestrationService.service;

import com.stackroute.orchestrationService.domain.User;
import com.stackroute.orchestrationService.exception.UserAlreadyExistException;
import org.springframework.stereotype.Service;

public interface OrchestrationService {
  User registerUser(User user) throws UserAlreadyExistException;
}
