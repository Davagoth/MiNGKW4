package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
