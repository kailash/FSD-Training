package com.stackroute.muzixservice.repository;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.muzixservice.domain.Artist;
import com.stackroute.muzixservice.domain.Image;
import com.stackroute.muzixservice.domain.Track;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MuzixRepositoryTest {

	@Autowired
	private MuzixRepository muzixRepository;

	private Image image;
	private Artist artist;
	private Track track;

	@Before
	public void setup() {
		image = new Image(1, "http:url", "large");
		artist = new Artist(101, "ArtistName", "http://artistURL", image);
		track = new Track("Track_1", "myNewTrack", "new Comments", "123", "http://trackURL", artist);
	}

	@After
	public void tearDown() {
		image = null;
		artist = null;
		track = null;
		muzixRepository.deleteAll();
	}

	@Test
	public void saveTrackTest() {
		muzixRepository.insert(track);
		Track fetchedTrack = muzixRepository.findById(track.getTrackId()).get();
		Assert.assertEquals(fetchedTrack.getTrackName(), track.getTrackName());
	}

	@Test
	public void updateCommentsTest() {
		muzixRepository.insert(track);
		Track fetchedTrack = muzixRepository.findById(track.getTrackId()).get();
		fetchedTrack.setComments("new comment updated");
		muzixRepository.save(fetchedTrack);
		fetchedTrack = muzixRepository.findById(track.getTrackId()).get();
		Assert.assertEquals("new comment updated", fetchedTrack.getComments());
	}
	
	@Test
	public void deleteTrackTest() {
		muzixRepository.insert(track);
		Track fetchedTrack = muzixRepository.findById(track.getTrackId()).get();
		muzixRepository.delete(fetchedTrack);
		Assert.assertEquals(Optional.empty(), muzixRepository.findById(fetchedTrack.getTrackId()));
	}
	
	@Test
	public void getAllTracksTest() {
		muzixRepository.insert(track);
		image = new Image(2, "http:imageUrl/2", "large_2");
		artist = new Artist(102, "ArtistName_2", "http://artistURL/2", image);
		track = new Track("Track_2", "my2NewTrack", "new Comments", "1234", "http://trackURL/2", artist);
		muzixRepository.insert(track);
		List<Track> tracks=muzixRepository.findAll();
		Assert.assertEquals(2, tracks.size());
		Assert.assertEquals("Track_2", tracks.get(1).getTrackId());
		Assert.assertEquals("Track_1", tracks.get(0).getTrackId());
	}

}
