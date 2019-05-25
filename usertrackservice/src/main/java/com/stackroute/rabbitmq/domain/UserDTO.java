package com.stackroute.rabbitmq.domain;

import java.util.List;

public class UserDTO {
  private String username;
  private String email;
  private String password;
  private List<TrackDTO> trackList;

  public List<TrackDTO> getTrackList() {
    return trackList;
  }

  public void setTrackList(List<TrackDTO> trackList) {
    this.trackList = trackList;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
