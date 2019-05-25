package com.stackroute.muzixservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.service.MuzixService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/muzixservice")
public class MuzixController {

	MuzixService muzixService;

	@Autowired
	public MuzixController(MuzixService muzixService) {
		this.muzixService = muzixService;
	}

	@PostMapping("track")
	ResponseEntity<?> saveTrackToWishList(@RequestBody Track track) throws TrackAlreadyExistException {
		muzixService.saveTrackToWishList(track);
		return ResponseEntity.status(HttpStatus.CREATED).body(track);
	}

	@DeleteMapping("track/{id}")
	ResponseEntity<?> deleteTrackFromWishList(@PathVariable("id") String id) throws TrackNotFoundException {
		muzixService.deleteTrackFromWishList(id);
		return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted !!!");
	}

	@PutMapping("track/{id}")
	ResponseEntity<?> updateCommentsForTrack(@RequestBody Track track, @PathVariable("id") String id)
			throws TrackNotFoundException {
		Track updatedTrack = muzixService.updateCommentForTrack(track.getComments(), id);
		return ResponseEntity.status(HttpStatus.OK).body(updatedTrack);
	}

	@GetMapping("tracks")
	ResponseEntity<?> getAllTracksfromWishList() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(muzixService.getAllTrackfromWishlist());
	}

}
