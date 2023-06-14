package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
