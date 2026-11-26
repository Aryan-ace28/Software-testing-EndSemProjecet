package com.aryan.api_automation.user;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User x) { return service.create(x); }

    @GetMapping
    public List<User> all() { return service.all(); }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @Valid @RequestBody User x) { return service.update(id, x); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
