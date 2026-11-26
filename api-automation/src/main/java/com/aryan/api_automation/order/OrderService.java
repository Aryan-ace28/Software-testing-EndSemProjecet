package com.aryan.api_automation.order;

import com.aryan.api_automation.product.ProductService;
import com.aryan.api_automation.user.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {
    private final OrderRepository repo;
    private final UserService userService;
    private final ProductService productService;

    public OrderService(OrderRepository repo, UserService userService, ProductService productService) {
        this.repo = repo;
        this.userService = userService;
        this.productService = productService;
    }

    public List<Order> all() { return repo.findAll(); }

    public Order get(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    public Order create(Order o) {
        userService.get(o.userId);
        productService.get(o.productId);
        o.id = null;
        o.status = "CREATED";
        return repo.save(o);
    }

    public Order update(Long id, Order o) {
        Order existing = get(id);
        userService.get(o.userId);
        productService.get(o.productId);
        o.id = id;
        o.status = existing.status;
        return repo.save(o);
    }

    public Order markPaid(Long id) {
        Order o = get(id);
        o.status = "PAID";
        return repo.save(o);
    }

    public void delete(Long id) { get(id); repo.deleteById(id); }
}
