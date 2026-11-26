package com.aryan.api_automation.product;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> all() { return repo.findAll(); }

    public Product get(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public Product create(Product x) { x.id = null; return repo.save(x); }

    public Product update(Long id, Product x) { get(id); x.id = id; return repo.save(x); }

    public void delete(Long id) { get(id); repo.deleteById(id); }
}
