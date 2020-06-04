package com.subhadip.rest.webservices.restmovierating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subhadip.rest.webservices.restmovierating.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
	
	List<Rating> findByMovieId(Integer movieId);
	List<Rating> findByCustomerId(Integer customerId);
	

}
