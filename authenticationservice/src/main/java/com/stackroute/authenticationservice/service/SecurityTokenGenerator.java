package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String,String> generateToken(User user);
}
