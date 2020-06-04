package com.subhadip.rest.webservices.restmovierating.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.subhadip.rest.webservices.restmovierating.domain.Customer;
import com.subhadip.rest.webservices.restmovierating.domain.Movie;
import com.subhadip.rest.webservices.restmovierating.domain.Rating;
import com.subhadip.rest.webservices.restmovierating.repository.CustomerRepository;
import com.subhadip.rest.webservices.restmovierating.repository.MovieRepository;
import com.subhadip.rest.webservices.restmovierating.repository.RatingRepository;

@Component
public class MovieDAOImp {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	CustomerRepository customerRepository;

	public void addRating(Rating rating) {
		ratingRepository.save(rating);
		//Update average movie rating
		List<Rating> movieRatings = ratingRepository.findByMovieId(rating.getMovieId());
		double avgMovieRating = movieRatings.stream().mapToInt(Rating::getRating).average().getAsDouble();
		Movie mov = movieRepository.findByMovieId(rating.getMovieId());
		mov.setAverageRating(avgMovieRating);
		movieRepository.save(mov);
		
		//Update average Customer rating
		List<Rating> customerRatings = ratingRepository.findByCustomerId(rating.getCustomerId());
		double avgCustomerRating = customerRatings.stream().mapToInt(Rating::getRating).average().getAsDouble();
		Customer cust = customerRepository.findByCustomerId(rating.getCustomerId());
		cust.setCustomerAverageRating(avgCustomerRating);
		customerRepository.save(cust);
		
		//Update combined average rating
		List<Customer> allCustomers = customerRepository.findAll();
		double combinedAvgRating = allCustomers.stream().mapToDouble(Customer::getCustomerAverageRating).average()
				.getAsDouble();
		customerRepository.updateCustomerAvgRating(combinedAvgRating);

	}

	public Optional<Movie> highestRatedMovie() {
		List<Movie> movieList = movieRepository.findAll();
		Optional<Movie> mov = movieList.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Movie::getAverageRating)));
		return mov;

	}
	
	public Optional<Customer> highestRatedCustomer() {
		List<Customer> customerList = customerRepository.findAll();
		Optional<Customer> cust = customerList.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Customer::getCustomerAverageRating)));
		return cust;

	}

}
