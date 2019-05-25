package com.stackroute.muzixservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.muzixservice.domain.Track;

public interface MuzixRepository extends MongoRepository<Track,String>{

}
