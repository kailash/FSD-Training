package com.stackroute.usertrackservice.controller;

import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistException;
import com.stackroute.usertrackservice.service.UserTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/usertrackservice/")
public class UserController{

    private UserTrackService userTrackService;
    private ResponseEntity responseEntity;

    @Autowired
    public UserController(UserTrackService userTrackService){
        this.userTrackService=userTrackService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) throws UserAlreadyExistException {
        try{
            userTrackService.registeruser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch(UserAlreadyExistException e){
            throw new UserAlreadyExistException();
        }
    }

    @PostMapping("/user/{username}/track")
    public ResponseEntity saveUserTrackToWishList(@RequestBody Track track,@PathVariable("username") String username) throws TrackAlreadyExistException {
        try{
            User user=userTrackService.saveUserTrackToWishList(username,track);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch(TrackAlreadyExistException e){
            throw new TrackAlreadyExistException();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/user/{username}/{trackId}")
    public ResponseEntity deleteUserTrackToWishList(@PathVariable("username") String username,@PathVariable("trackId") String trackId) throws  TrackNotFoundException {
        try{
            User user=userTrackService.deleteUserTrackFromWishList(username,trackId);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch(TrackNotFoundException e){
            throw new TrackNotFoundException();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PatchMapping("/user/{username}/track")
    public ResponseEntity updateCommentForUserTrack(@RequestBody Track track,@PathVariable("username") String username) throws  TrackNotFoundException {
        try{
            User user=userTrackService.updateCommentForTrack(username,track.getTrackId(),track.getComments());
            return ResponseEntity.status(HttpStatus.OK).body(track);
        }catch(TrackNotFoundException e){
            throw new TrackNotFoundException();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/user/{username}/tracks")
    public ResponseEntity getAllTracks(@PathVariable("username") String username) {
        try{

            return ResponseEntity.status(HttpStatus.OK).body(userTrackService.getAllUserTrackFromWishlist(username));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


}


