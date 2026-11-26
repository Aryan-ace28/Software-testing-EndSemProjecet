package com.aryan.api_automation.payment;

import com.aryan.api_automation.order.OrderService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PaymentService {
    private final PaymentRepository repo;
    private final OrderService orderService;

    public PaymentService(PaymentRepository repo, OrderService orderService) {
        this.repo = repo;
        this.orderService = orderService;
    }

    public List<Payment> all() { return repo.findAll(); }

    public Payment get(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
    }

    public Payment create(Payment p) {
        orderService.get(p.orderId);
        p.id = null;
        p.status = "SUCCESS";
        Payment saved = repo.save(p);
        orderService.markPaid(p.orderId);
        return saved;
    }

    public Payment update(Long id, Payment p) {
        Payment existing = get(id);
        orderService.get(p.orderId);
        p.id = id;
        p.status = existing.status;
        return repo.save(p);
    }

    public void delete(Long id) { get(id); repo.deleteById(id); }
}
