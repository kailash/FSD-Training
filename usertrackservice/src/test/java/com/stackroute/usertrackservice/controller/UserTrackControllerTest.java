package com.stackroute.usertrackservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.usertrackservice.controller.UserController;
import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.service.UserTrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserTrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserTrackService userTrackService;

    private Artist artist;
    private Image image;
    private Track track;
    private User user;
    private List<Track> trackList;

    @Before
    public void setup(){
        trackList=new ArrayList<>();
        image=new Image(1,"http:url","large");
        artist = new Artist(1,"john","http:url",image);
        track=new Track("123","abc","comments","listners","http://url",artist);

        trackList.add(track);
        image=new Image(2,"http:url","large");
        artist = new Artist(2,"mark","http:url",image);
        track=new Track("345","xyz","comments","listners","http://url",artist);
        trackList.add(track);
        user=new User("john","abc@gmail.com",trackList);
    }


    @After
    public void tearDown(){
        image=null;
        track=null;
        artist=null;
        user=null;
    }

    @Test
    public void saveUserTrackTest() throws Exception {
        when(userTrackService.saveUserTrackToWishList(eq(user.getUsername()),any())).thenReturn(user);
        mockMvc.perform(post("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(userTrackService,times(1)).saveUserTrackToWishList(eq(user.getUsername()),any());

    }

    @Test
    public void updateCommentTest() throws Exception {
        when(userTrackService.updateCommentForTrack(user.getUsername(),track.getTrackId(),track.getComments())).thenReturn(user);
        mockMvc.perform(patch("/api/v1/usertrackservice/user/{username}/track",user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userTrackService,times(1)).updateCommentForTrack(user.getUsername(),track.getTrackId(),track.getComments());

    }

    @Test
    public void deleteTrackFromWishlistTest() throws Exception {
        when(userTrackService.deleteUserTrackFromWishList(user.getUsername(),track.getTrackId())).thenReturn(user);
        mockMvc.perform(delete("/api/v1/usertrackservice/user/{username}/{trackId}",user.getUsername(),track.getTrackId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userTrackService,times(1)).deleteUserTrackFromWishList(user.getUsername(),track.getTrackId());

    }

    @Test
    public void getAllTrackTest() throws Exception {
        when(userTrackService.getAllUserTrackFromWishlist(eq(user.getUsername()))).thenReturn(trackList);
        mockMvc.perform(get("/api/v1/usertrackservice/user/{username}/tracks",user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userTrackService,times(1)).getAllUserTrackFromWishlist(eq(user.getUsername()));

    }

    private static String jsonToString(final Object obj) {
        String result = null;
        try {
            final ObjectMapper objectmapper = new ObjectMapper();
            final String jsonContent=objectmapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;

    }



}
