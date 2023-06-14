package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    @Transactional
    void deleteByProductEntityId(long id);
    CartItemEntity findByProductEntityIdAndCartEntityId(long productEntityId, long cartEntityId);
}
