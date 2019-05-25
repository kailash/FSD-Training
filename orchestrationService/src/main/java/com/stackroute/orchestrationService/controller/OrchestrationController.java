package com.stackroute.orchestrationService.controller;


import com.stackroute.orchestrationService.domain.User;
import com.stackroute.orchestrationService.exception.UserAlreadyExistException;
import com.stackroute.orchestrationService.service.OrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class OrchestrationController {

  OrchestrationService orchestrationService;

  @Autowired
  public OrchestrationController(OrchestrationService orchestrationService){
    this.orchestrationService=orchestrationService;
  }

  @PostMapping("/user")
  public ResponseEntity<?> saveAndRegister(@RequestBody User user) throws UserAlreadyExistException {
      try{
        User userObj=this.orchestrationService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userObj);
      }catch(UserAlreadyExistException e){
        throw new UserAlreadyExistException();
      }
  }

}
