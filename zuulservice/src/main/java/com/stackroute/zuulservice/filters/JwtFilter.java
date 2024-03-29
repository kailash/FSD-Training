package com.stackroute.zuulservice.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    final HttpServletRequest httpRequest=(HttpServletRequest)servletRequest;
    final HttpServletResponse httpResponse=(HttpServletResponse)servletResponse;
    final String authHeader=httpRequest.getHeader("authorization");
    if("OPTIONS".equals(httpRequest.getMethod())){
      httpResponse.setStatus(HttpServletResponse.SC_OK);
      filterChain.doFilter(httpRequest,httpResponse);
    }else{
      if(authHeader==null || !authHeader.startsWith("Bearer ")){
        throw new ServletException("Missing or invalid Authorization header");
      }

      final String token=authHeader.substring(7);
      final Claims claims= Jwts.parser().setSigningKey("secreatkey").parseClaimsJws(token).getBody();
      httpRequest.setAttribute("claims",claims);
      filterChain.doFilter(httpRequest,httpResponse);
    }

  }
}
