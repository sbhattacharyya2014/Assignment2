package com.subhadip.rest.webservices.restmovierating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.subhadip.rest.webservices.restmovierating.domain.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer  findByCustomerId(Integer customerId);
	
	@Modifying
	@Query("update Customer cus  set cus.averageRating= ?")
	int updateCustomerAvgRating(double avgRating);
}
