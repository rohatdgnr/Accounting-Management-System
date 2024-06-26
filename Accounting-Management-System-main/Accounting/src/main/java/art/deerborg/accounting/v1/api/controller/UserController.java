package art.deerborg.accounting.v1.api.controller;

import art.deerborg.accounting.v1.api.model.User;
import art.deerborg.accounting.v1.api.service.abstracts.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getByUserId(id);
    }
}
