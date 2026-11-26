package com.aryan.api_automation.product;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product x) { return service.create(x); }

    @GetMapping
    public List<Product> all() { return service.all(); }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @Valid @RequestBody Product x) { return service.update(id, x); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
