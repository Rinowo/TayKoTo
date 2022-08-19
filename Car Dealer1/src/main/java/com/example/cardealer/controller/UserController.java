package com.example.cardealer.controller;

import com.example.cardealer.dto.Constants;
import com.example.cardealer.dto.RegisterUser;
import com.example.cardealer.model.Customer;
import com.example.cardealer.model.Roles;
import com.example.cardealer.model.Users;
import com.example.cardealer.service.CustomerServiceImpl;
import com.example.cardealer.service.RoleServiceImpl;
import com.example.cardealer.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping(value = "/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerSubmit(@ModelAttribute RegisterUser registerUser) {
        if (usersService.existsByUsername(registerUser.getUsername())) {

        } else {
            Users users = new Users();
            users.setUsername(registerUser.getUsername());
            users.setEmail(registerUser.getEmail());
            String password = encoder.encode(registerUser.getPassword());
            users.setPassword(password);

            Set<Roles> roles = new HashSet<>();
            Roles r = roleService.findByRoleName(Constants.ROLE_CUSTOMER).get();
            roles.add(r);

            users.setRoles(roles);

            Customer customer = new Customer();
            customer.setCustomerEmail(users.getEmail());
            customerService.saveCustomer(customer);

            users.setCustomerId(customer.getCustomerId());
            usersService.saveUsers(users);
        }
        return "redirect:/home";
    }
}
