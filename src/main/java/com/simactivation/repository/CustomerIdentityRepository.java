package com.simactivation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.simactivation.model.CustomerIdentity;

@Repository
public interface CustomerIdentityRepository extends JpaRepository<CustomerIdentity, Integer> {
	CustomerIdentity findByUniqueIdNumber(long uniqueidnumber);
	CustomerIdentity findByFirstName(long firstname);
	CustomerIdentity findByLastName(long lastname);
}
