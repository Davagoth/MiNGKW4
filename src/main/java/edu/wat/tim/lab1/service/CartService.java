package edu.wat.tim.lab1.service;

import edu.wat.tim.lab1.model.CartEntity;
import edu.wat.tim.lab1.model.CartItemEntity;
import edu.wat.tim.lab1.model.CustomerEntity;
import edu.wat.tim.lab1.model.ProductEntity;
import edu.wat.tim.lab1.repository.CartItemRepository;
import edu.wat.tim.lab1.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CustomerService customerService;
    private final CartRepository cartRepository;

    @Transactional
    public CartEntity createCart(Long customerId, CartEntity cartEntity) {
        CustomerEntity customerEntity = customerService.getCustomerById(customerId);
        cartEntity.setCustomerEntity(customerEntity);
        return cartRepository.save(cartEntity);
    }
}
