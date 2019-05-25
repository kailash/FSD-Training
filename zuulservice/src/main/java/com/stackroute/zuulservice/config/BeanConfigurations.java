package com.stackroute.zuulservice.config;

import com.stackroute.zuulservice.filters.ErrorFilter;
import com.stackroute.zuulservice.filters.PostFilter;
import com.stackroute.zuulservice.filters.PreFilter;
import com.stackroute.zuulservice.filters.RouterFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class BeanConfigurations {

  @Bean
  public PreFilter preFilter(){
    return new PreFilter();
  }

  @Bean
  public PostFilter postFilter(){
    return new PostFilter();
  }

  @Bean
  public RouterFilter routeFilter(){
    return new RouterFilter();
  }

  @Bean
  public ErrorFilter errorFilter(){
    return new ErrorFilter();
  }

  @Bean
  public CorsFilter corsFilter(){
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config=new CorsConfiguration();

    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");

    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("HEAD");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PATCH");
    source.registerCorsConfiguration("*",config);
    return new CorsFilter(source);
  }




}
