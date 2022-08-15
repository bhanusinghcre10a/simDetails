package com.simactivation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.simactivation.model.SimDetails;

@Repository
public interface SimDetailsRepository extends JpaRepository<SimDetails, Integer>{
	SimDetails findByServiceNumber(long serviceNumber);
}
