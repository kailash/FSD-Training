package com.stackroute.usertrackservice.service;

import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserTrackService {
    User saveUserTrackToWishList(String username, Track track) throws TrackAlreadyExistException;
    User deleteUserTrackFromWishList(String usernMae,String trackId) throws TrackNotFoundException;
    User updateCommentForTrack(String username,String trackId,String comment) throws TrackNotFoundException;
    List<Track> getAllUserTrackFromWishlist(String username) throws Exception;
    User registeruser(User user) throws UserAlreadyExistException;
}
