package com.stackroute.muzixservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixservice.domain.Artist;
import com.stackroute.muzixservice.domain.Image;
import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.service.MuzixService;

@RunWith(SpringRunner.class)
@WebMvcTest(MuzixController.class)
public class MuzixControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MuzixService muzixService;
	
	private Image image;
	private Artist artist;
	private Track track;
	private List<Track> tracks;

	@Before
	public void setUp() throws Exception {
		tracks=new ArrayList<Track>();
		
		image = new Image(11, "http:url", "large");
		artist = new Artist(1011, "ArtistName", "http://artistURL", image);
		track = new Track("Track_11", "myNewTrack", "new Comments", "123", "http://trackURL", artist);
		tracks.add(track);
		
		image = new Image(2, "http:url/2", "large_2");
		artist = new Artist(201, "ArtistName_2", "http://artistURL/2", image);
		track = new Track("Track_21", "myNewTrack", "new Comments", "123", "http://trackURL/2", artist);
		tracks.add(track);
	}

	@After
	public void tearDown() throws Exception {
		image=null;
		artist=null;
		track=null;
	}

	@Test
	public void saveTrackToWishListTest() throws JsonProcessingException, Exception {
		when(muzixService.saveTrackToWishList(any())).thenReturn(track);
		mockMvc.perform(post("/api/v1/muzixservice/track")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(jsonToString(track)))
		       .andExpect(status().isCreated())
			   .andDo(print());
		
		verify(muzixService,times(1)).saveTrackToWishList(any());
	}
	
	@Test
	public void saveTrackToWishListFailureTest() throws Exception {
		when(muzixService.saveTrackToWishList(any())).thenThrow(TrackAlreadyExistException.class);
		mockMvc.perform(post("/api/v1/muzixservice/track")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonToString(track)))
		.andExpect(status().isConflict())
		.andDo(print());
		
		verify(muzixService,times(1)).saveTrackToWishList(any());
	}

	@Test
	public void deleteTrackFromWishListTest() throws Exception {
		when(muzixService.deleteTrackFromWishList(track.getTrackId())).thenReturn(true);
		
		mockMvc.perform(delete("/api/v1/muzixservice/track/Track_21")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(track)))
				.andExpect(status().isOk())
				.andDo(print());
		
		verify(muzixService,times(1)).deleteTrackFromWishList(track.getTrackId());
	}
	
	@Test
	public void deleteTrackFromWishListFailureTest() throws Exception {
		when(muzixService.deleteTrackFromWishList(track.getTrackId())).thenThrow(TrackNotFoundException.class);
		
		mockMvc.perform(delete("/api/v1/muzixservice/track/Track_21")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(track)))
				.andExpect(status().isNotFound())
				.andDo(print());
		
		verify(muzixService,times(1)).deleteTrackFromWishList(track.getTrackId());
	}
	

	@Test
	public void updateCommentsForTrackTest() throws Exception {
		when(muzixService.updateCommentForTrack((track.getComments()),(track.getTrackId()))).thenReturn(track);
		
		mockMvc.perform(put("/api/v1/muzixservice/track/Track_21")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(track)))
				.andExpect(status().isOk())
				.andDo(print());
		
		verify(muzixService,times(1)).updateCommentForTrack(track.getComments(), track.getTrackId());
		
	}
	
	@Test
	public void updateCommentsForTrackFailureTest() throws Exception {
		when(muzixService.updateCommentForTrack((track.getComments()),(track.getTrackId()))).thenThrow(TrackNotFoundException.class);
		
		mockMvc.perform(put("/api/v1/muzixservice/track/Track_21")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(track)))
				.andExpect(status().isNotFound())
				.andDo(print());
		
		verify(muzixService,times(1)).updateCommentForTrack(track.getComments(), track.getTrackId());
		
	}
	

	@Test
	public void getAllTracksfromWishListTest() throws Exception {
		when(muzixService.getAllTrackfromWishlist()).thenReturn(tracks);
		
		mockMvc.perform(get("/api/v1/muzixservice/tracks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(track)))
				.andExpect(status().isOk())
				.andDo(print());
		
		verify(muzixService,times(1)).getAllTrackfromWishlist();
	}
	
	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String result;
		ObjectMapper mapper=new ObjectMapper();
		String jsonContent=mapper.writeValueAsString(obj);
		result=jsonContent;
		return result;
	}

}
