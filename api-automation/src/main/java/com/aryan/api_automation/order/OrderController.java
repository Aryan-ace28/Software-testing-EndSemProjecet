package com.aryan.api_automation.order;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody Order x) { return service.create(x); }

    @GetMapping
    public List<Order> all() { return service.all(); }

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @Valid @RequestBody Order x) { return service.update(id, x); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
