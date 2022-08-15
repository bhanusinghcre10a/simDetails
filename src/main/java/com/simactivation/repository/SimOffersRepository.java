package com.simactivation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.simactivation.model.SimOffers;

@Repository
public interface SimOffersRepository extends JpaRepository<SimOffers, Integer>{
	SimOffers findBySimId(int simId);
}
