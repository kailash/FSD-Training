package com.stackroute.muzixservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;

@Service
public class MuzixServiceImpl implements MuzixService {
	
	
	MuzixRepository muzixRepository;
	@Autowired
	public MuzixServiceImpl(MuzixRepository muzixrepository){
		this.muzixRepository=muzixrepository;
	}

	@Override
	public Track saveTrackToWishList(Track track) throws TrackAlreadyExistException {
		Optional optional=muzixRepository.findById(track.getTrackId());
		if(optional.isPresent()) {
			throw new TrackAlreadyExistException();
		}
		muzixRepository.insert(track);
		return track;
	}

	@Override
	public boolean deleteTrackFromWishList(String id) throws TrackNotFoundException {
		Optional optional=muzixRepository.findById(id);
		if(!optional.isPresent()) {
			throw new TrackNotFoundException();
		}
		muzixRepository.deleteById(id);
		return true;
	}

	@Override
	public Track updateCommentForTrack(String comments, String id) throws TrackNotFoundException {
		Track track=null;
		Optional optional=muzixRepository.findById(id);
		if(!optional.isPresent()) {
			throw new TrackNotFoundException();
		}
		track=muzixRepository.findById(id).get();
		track.setComments(comments);
		
		muzixRepository.save(track);
		return track;
	}

	@Override
	public List<Track> getAllTrackfromWishlist() throws Exception {
		return muzixRepository.findAll();
	}

}
