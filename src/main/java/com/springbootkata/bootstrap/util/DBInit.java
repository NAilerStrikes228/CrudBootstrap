package com.springbootkata.bootstrap.util;

import com.springbootkata.bootstrap.model.Role;
import com.springbootkata.bootstrap.model.User;
import com.springbootkata.bootstrap.service.RoleService;
import com.springbootkata.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);

        User newUser = new User("Billy", "Herrington", 45, "billy@mail.com", "User",
                "user", userSet);
        User admin = new User("Van", "Darkholm", 31, "van@gmail.com", "admin",
                "admin", adminSet);

        userService.saveUser(newUser);
        userService.saveUser(admin);
    }
}
