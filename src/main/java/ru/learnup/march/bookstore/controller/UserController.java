package ru.learnup.march.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.learnup.march.bookstore.entity.Role;
import ru.learnup.march.bookstore.entity.User;
import ru.learnup.march.bookstore.services.RoleService;
import ru.learnup.march.bookstore.services.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @RequestMapping("/createDefaultUser")
    public boolean createUser() {
        Role role = roleService.findByRole("ROLE_USER");
        User user = new User();
        user.setUsername("den");
        user.setPassword("1");
        user.setRole(role);
        userService.createUser(user);
        return true;
    }
}
