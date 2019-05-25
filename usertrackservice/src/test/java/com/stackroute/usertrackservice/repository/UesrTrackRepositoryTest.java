package com.stackroute.usertrackservice.repository;

import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UesrTrackRepositoryTest {
    @Autowired
    UserTrackRepository userTrackRepository;

    Track track;
    User user;

    @Before
    public void setup(){
        Image image =new Image(1,"http:url","large");
        Artist artist=new Artist(101,"john","http:urlA",image);
        Track track=new Track("track123","myNewTrack","new comment","123","http:trackURL",artist);

        ArrayList<Track> list=new ArrayList<>();
        list.add(track);
        user=new User("john","john@gmail.com",list);
    }

    @After
    public void tearDown(){
        userTrackRepository.deleteAll();
    }

    @Test
    public void saveUserTrackTest(){
        userTrackRepository.save(user);
        User fetchedUser=userTrackRepository.findByUsername(user.getUsername());
        List<Track> fetchedList=fetchedUser.getTrackList();
        Assert.assertEquals(fetchedList.get(0).getTrackId(),fetchedList.get(0).getTrackId());
    }


}
