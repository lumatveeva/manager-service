package ru.lumat.managerservice.clients;

import ru.lumat.managerservice.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRestClient {

    List<Product> findAllProducts(String filter);

    Optional<Product> findProduct(int productId);

    Product createProduct(String title, String details);

    void updateProduct(int productId, String title, String details);

    void deleteProduct(int productId);
}
