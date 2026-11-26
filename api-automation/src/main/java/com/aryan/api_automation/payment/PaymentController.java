package com.aryan.api_automation.payment;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Payment create(@Valid @RequestBody Payment x) { return service.create(x); }

    @GetMapping
    public List<Payment> all() { return service.all(); }

    @GetMapping("/{id}")
    public Payment get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public Payment update(@PathVariable Long id, @Valid @RequestBody Payment x) { return service.update(id, x); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
