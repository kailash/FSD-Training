package com.stackroute.usertrackservice.service;

import com.stackroute.rabbitmq.domain.UserDTO;
import com.stackroute.usertrackservice.config.Producer;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistException;
import com.stackroute.usertrackservice.repository.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTrackServiceImpl implements UserTrackService{

    UserTrackRepository userTrackRepository;
    Producer producer;

    @Autowired
    public UserTrackServiceImpl(UserTrackRepository userTrackRepository,Producer producer) {
        this.userTrackRepository = userTrackRepository;
        this.producer=producer;
    }

    @Override
    public User saveUserTrackToWishList(String username, Track track) throws TrackAlreadyExistException {
       User fetchedUser=userTrackRepository.findByUsername(username);
       List<Track> fetchedTracks=fetchedUser.getTrackList();
       if(fetchedTracks!=null){
           for(Track trackObj:fetchedTracks){
               if(trackObj.getTrackId().equals(track.getTrackId())){
                    throw new TrackAlreadyExistException();
               }
           }
           fetchedTracks.add(track);
           fetchedUser.setTrackList(fetchedTracks);

           UserDTO userDTO=new UserDTO();
           userDTO.setUsername(fetchedUser.getUsername());
           userDTO.setEmail(fetchedUser.getEmail());
           List list=new ArrayList();
           list.add(fetchedTracks);
           userDTO.setTrackList(list);

           userTrackRepository.save(fetchedUser);
           producer.send2RabbitMqTrackObject(userDTO);
       }else{
           fetchedTracks=new ArrayList<>();
           fetchedTracks.add(track);
           fetchedUser.setTrackList(fetchedTracks);
           userTrackRepository.save(fetchedUser);
       }
       return fetchedUser;
    }

    @Override
    public User deleteUserTrackFromWishList(String username, String trackId) throws TrackNotFoundException {
        User fetchedUser=userTrackRepository.findByUsername(username);
        List<Track> fetchedTracks=fetchedUser.getTrackList();

        if(fetchedTracks.size()>0){
            for(int i=0;i<fetchedTracks.size();i++){
                if(trackId.equals(fetchedTracks.get(i).getTrackId())){
                    fetchedTracks.remove(i);
                    fetchedUser.setTrackList(fetchedTracks);
                    userTrackRepository.save(fetchedUser);
                    break;
                }
            }

        }else{
            throw new TrackNotFoundException();

        }
        return fetchedUser;
    }

    @Override
    public User updateCommentForTrack(String username, String trackId, String comment) throws TrackNotFoundException {
        User fetchedUser=userTrackRepository.findByUsername(username);
        List<Track> fetchedTracks=fetchedUser.getTrackList();

        if(fetchedTracks.size()>0){
            for(int i=0;i<fetchedTracks.size();i++){
                if(trackId.equals(fetchedTracks.get(i).getTrackId())){
                    fetchedTracks.get(i).setComments(comment);
                    userTrackRepository.save(fetchedUser);
                    break;
                }
            }

        }else{
            throw new TrackNotFoundException();

        }
        return fetchedUser;
    }

    @Override
    public List<Track> getAllUserTrackFromWishlist(String username) throws Exception {
       return userTrackRepository.findByUsername(username).getTrackList();
    }

    @Override
    public User registeruser(User user) throws UserAlreadyExistException {
        UserDTO userDTO=new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        User fetchedObj=userTrackRepository.findByUsername(user.getUsername());
        if(fetchedObj!=null){
            throw new UserAlreadyExistException();
        }else{
            userTrackRepository.save(user);
            producer.sendMessagetoRabbitMq(userDTO);
        }
        return user;
    }
}
