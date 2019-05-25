package com.stackroute.usertrackservice.service;

import com.stackroute.usertrackservice.config.Producer;
import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.repository.UserTrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserTrackServiceTest {

    @Mock
    private UserTrackRepository userTrackRepository;

    @Mock
    Producer producer;

    private User user;
    private Track track;
    private Artist artist;
    private List<Track> list;

    @InjectMocks
    UserTrackServiceImpl userTrackService;

    @Before
    public void setup(){

        MockitoAnnotations.initMocks(this);

        Image image =new Image(1,"http:url","large");
         artist=new Artist(101,"john","http:urlA",image);
        track=new Track("track123","myNewTrack","new comment","123","http:trackURL",artist);

         list=new ArrayList<>();
        list.add(track);
         user=new User("john","john@gmail.com",list);
    }

    @After
    public void tearDown(){
        userTrackRepository.deleteAll();
        list=null;
        artist=null;
        track=null;
        user=null;

    }


    @Test
    public void saveUserTrackSuccessTest() throws TrackAlreadyExistException {
        user=new User("josh123","josh@gmail.com",null);
        when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
        User fetchedUser=userTrackService.saveUserTrackToWishList(user.getUsername(),track);
        Assert.assertEquals(fetchedUser,user);
        verify(userTrackRepository,timeout(1)).findByUsername(user.getUsername());
        verify(userTrackRepository,times(1)).save(user);
    }

    @Test
    public void deleteUserTrackFromWishListTest() throws TrackAlreadyExistException, TrackNotFoundException {

        when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
        User fetchedUser=userTrackService.deleteUserTrackFromWishList(user.getUsername(),track.getTrackId());
        Assert.assertEquals(fetchedUser,user);
        verify(userTrackRepository,times(1)).findByUsername(user.getUsername());
        verify(userTrackRepository,times(1)).save(user);
    }

    @Test
    public void updateCommentForTrackTest() throws  TrackNotFoundException {

        when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
        User fetchedUser=userTrackService.updateCommentForTrack(user.getUsername(),track.getTrackId(),"new comment");
        Assert.assertEquals(fetchedUser.getTrackList().get(0).getComments(),"new comment");
        verify(userTrackRepository,times(1)).findByUsername(user.getUsername());
        verify(userTrackRepository,times(1)).save(user);
    }

    @Test
    public void getAllTrackListTest() throws Exception {
        when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
         List<Track> fetchedList  =userTrackService.getAllUserTrackFromWishlist(user.getUsername());
        Assert.assertEquals(fetchedList,list);
        verify(userTrackRepository,times(1)).findByUsername(user.getUsername());

    }


}
