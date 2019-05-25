package com.stackroute.muzixservice.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.muzixservice.domain.Artist;
import com.stackroute.muzixservice.domain.Image;
import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;

public class MuzixServiceTest {

	@Mock
	private MuzixRepository muzixRepository;

	private Track track;
	private Image image;
	private Artist artist;

	private Optional optional;
	private List<Track> trackLists = null;

	@InjectMocks
	private MuzixServiceImpl muzixImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		image = new Image(1, "http:url", "large");
		artist = new Artist(101, "ArtistName", "http://artistURL", image);
		track = new Track("Track_1", "myNewTrack", "new Comments", "123", "http://trackURL", artist);
		trackLists = new ArrayList<>();
		trackLists.add(track);
		optional = Optional.of(track);
	}

	@After
	public void tearDown() {
		image = null;
		artist = null;
		track = null;
	}

	@Test
	public void saveTrackSucccessTest() throws TrackAlreadyExistException {
		when(muzixRepository.insert(track)).thenReturn(track);

		Track fetchedTrack = muzixImpl.saveTrackToWishList(track);

		Assert.assertEquals(track, fetchedTrack);
		verify(muzixRepository, times(1)).insert(track);
		verify(muzixRepository, times(1)).findById(track.getTrackId());
	}

	@Test(expected = TrackAlreadyExistException.class)
	public void saveTrackFailureTest() throws TrackAlreadyExistException {
		when(muzixRepository.insert(track)).thenReturn(track);
		when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
		Track fetchedTrack = muzixImpl.saveTrackToWishList(track);
		Assert.assertEquals(track, fetchedTrack);

		verify(muzixRepository, times(1)).insert(track);
		verify(muzixRepository, times(1)).findById(track.getTrackId());
	}

	@Test
	public void updateCommentsSuccessTest() throws TrackNotFoundException {
		when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
		track.setComments("comments updated");
		Track fetchTrack = muzixImpl.updateCommentForTrack(track.getComments(), track.getTrackId());

		Assert.assertEquals(fetchTrack.getComments(), "comments updated");

		verify(muzixRepository, times(1)).save(track);
		verify(muzixRepository, times(2)).findById(track.getTrackId());

	}

	@Test
	public void deleteTrackTest() throws TrackNotFoundException {
		when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
		
		boolean fetchTrack=muzixImpl.deleteTrackFromWishList(track.getTrackId());
		Assert.assertEquals(true, fetchTrack);
		
		verify(muzixRepository,times(1)).findById(track.getTrackId());
		verify(muzixRepository,times(1)).deleteById(track.getTrackId());
	}
	
	@Test
	public void testgetAllTracks() throws Exception {
		when(muzixRepository.findAll()).thenReturn(trackLists);
		
		List<Track> list=muzixImpl.getAllTrackfromWishlist();
		Assert.assertEquals(list, trackLists);
		
		verify(muzixRepository,times(1)).findAll();
	}
}
