package edu.wat.tim.lab1.controller;

import edu.wat.tim.lab1.model.CartEntity;
import edu.wat.tim.lab1.model.CartItemEntity;
import edu.wat.tim.lab1.model.CustomerEntity;
import edu.wat.tim.lab1.model.ProductEntity;
import edu.wat.tim.lab1.service.CartService;
import edu.wat.tim.lab1.service.CustomerService;
import edu.wat.tim.lab1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {

    private final CustomerService customerService;
    private final CartService cartService;
    private final ProductService productService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity customerEntity) {
        CustomerEntity savedCustomerEntity = customerService.createCustomer(customerEntity);
        return new ResponseEntity<>(savedCustomerEntity, HttpStatus.CREATED);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity productEntity) {
        ProductEntity savedProductEntity = productService.createProduct(productEntity);
        return new ResponseEntity<>(savedProductEntity, HttpStatus.CREATED);
    }

    @PostMapping("/customers/{customerId}/carts")
    public ResponseEntity<CartEntity> createCart(@PathVariable Long customerId, @RequestBody CartEntity cartEntity) {
        CartEntity savedCartEntity = cartService.createCart(customerId, cartEntity);
        return new ResponseEntity<>(savedCartEntity, HttpStatus.OK);
    }

    @PostMapping("/carts/{cartId}/products/{productId}")
    public ResponseEntity<ProductEntity> addProductToCartById(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
        ProductEntity addedProduct = productService.addProductById(productId, cartId, quantity);
        return new ResponseEntity<>(addedProduct, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductEntity>> searchProducts(@RequestParam String name) {
        List<ProductEntity> productEntities = productService.searchProductsByName(name);
        return new ResponseEntity<>(productEntities, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/carts/{cartId}/products/{productId}")
    public ResponseEntity<CartItemEntity> updateProductQuantityInCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
        CartItemEntity cartItemEntity = productService.updateProductQuantity(productId, cartId, quantity);
        return new ResponseEntity<>(cartItemEntity, HttpStatus.OK);
    }
}
