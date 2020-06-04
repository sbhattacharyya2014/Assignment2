package com.subhadip.rest.webservices.restmovierating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subhadip.rest.webservices.restmovierating.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	Movie findByMovieId(Integer movieId);

}
