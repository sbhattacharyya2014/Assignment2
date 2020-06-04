package com.subhadip.rest.webservices.restmovierating.controler;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.subhadip.rest.webservices.restmovierating.domain.Customer;
import com.subhadip.rest.webservices.restmovierating.domain.Movie;
import com.subhadip.rest.webservices.restmovierating.domain.Rating;
import com.subhadip.rest.webservices.restmovierating.service.MovieDAOImp;


@RestController
public class CustomerController {

	
	@Autowired
	private MovieDAOImp movieService;
	
	
	@PostMapping("/api/rest/customer/{customerId}/rate/{movieId}")
	public void addRating(@PathVariable int customerId ,@PathVariable int movieId , @RequestBody Rating rate){
		
		rate.setCustomerId(customerId);
		rate.setMovieId(movieId);
		movieService.addRating(rate);
		
	}
	
	@GetMapping("/api/rest/movie/highest-rated")
	public Optional<Movie> fetchHighestRatedMovie(){
		return movieService.highestRatedMovie();
	}
	
	@GetMapping("/api/rest/customer/highest-rated")
	public Optional<Customer> fetchHighestRatedCustomer(){
		return movieService.highestRatedCustomer();
	}
	
	

}
