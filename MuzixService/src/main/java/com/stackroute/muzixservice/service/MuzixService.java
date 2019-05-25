package com.stackroute.muzixservice.service;

import java.util.List;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;

public interface MuzixService {

	Track saveTrackToWishList(Track track) throws TrackAlreadyExistException;
	
	boolean deleteTrackFromWishList(String id) throws TrackNotFoundException;
	
	Track updateCommentForTrack(String comments,String id) throws TrackNotFoundException;
	
	List<Track> getAllTrackfromWishlist() throws Exception;
}
