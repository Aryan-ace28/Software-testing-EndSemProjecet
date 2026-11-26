package com.aryan.api_automation.user;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public List<User> all() { return repo.findAll(); }

    public User get(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User create(User x) { x.id = null; return repo.save(x); }

    public User update(Long id, User x) { get(id); x.id = id; return repo.save(x); }

    public void delete(Long id) { get(id); repo.deleteById(id); }
}
