package edu.wat.tim.lab1.service;

import edu.wat.tim.lab1.model.CartEntity;
import edu.wat.tim.lab1.model.CartItemEntity;
import edu.wat.tim.lab1.model.CustomerEntity;
import edu.wat.tim.lab1.model.ProductEntity;
import edu.wat.tim.lab1.repository.CartItemRepository;
import edu.wat.tim.lab1.repository.CartRepository;
import edu.wat.tim.lab1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public ProductEntity createProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public List<ProductEntity> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public ProductEntity addProductById(long productId, long cartId, int quantity) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product with ID " + productId + " not found"));

        CartEntity cartEntity = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart with ID " + cartId + " not found"));

        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setProductEntity(productEntity);
        cartItemEntity.setCartEntity(cartEntity);
        cartItemEntity.setQuantity(quantity);

        cartItemRepository.save(cartItemEntity);

        return productEntity;
    }

    public void deleteProduct(long articleId) {
        cartItemRepository.deleteByProductEntityId(articleId);
        productRepository.deleteById(articleId);
    }

    public CartItemEntity updateProductQuantity(long productId, long cartId, int newQuantity) {
        if(newQuantity < 1){
            throw new RuntimeException(("Wrong Product Amount: ") + newQuantity);
        }
        CartItemEntity cartItemEntity = cartItemRepository.findByProductEntityIdAndCartEntityId(productId, cartId);
        cartItemEntity.setQuantity(newQuantity);
        return cartItemRepository.save(cartItemEntity);
    }
}
