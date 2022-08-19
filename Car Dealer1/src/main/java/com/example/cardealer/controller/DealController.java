package com.example.cardealer.controller;

import com.example.cardealer.model.*;
import com.example.cardealer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class DealController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private DealServiceImpl dealService;

    @Autowired
    private InfoServiceImpl infoService;

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private VehicleServiceImpl vehicleService;

    @Autowired
    private GalleryServiceImpl galleryService;

    @RequestMapping(path = "/car/{id}", method = RequestMethod.GET)
    public String detailVehicleCustomer(@PathVariable Long id,
                                        Model model,
                                        Deal deal,
                                        BindingResult result) {
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        Optional<InfoService> info = infoService.findByVehicleId(id);
        List<VehicleGallery> gallery = galleryService.getAllByVehicleId(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = usersService.findByUsername(username);
        Customer customer = customerService.getOne(user.getCustomerId());

        if (info.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
            model.addAttribute("info", info.get());
            model.addAttribute("gallery", gallery);
            model.addAttribute("user", user);
            model.addAttribute("customer", customer);
            model.addAttribute("deal", deal);
            return "car-detail-customer";
        } else {
            return "/";
        }
    }

    @PostMapping(path = "/submit-order/{id}")
    public String submitOrder(@PathVariable Long id,
                              @Valid Deal deal,
                              BindingResult bindingResult){
        LocalDate localDate = LocalDate.now();
        Optional<Vehicle> vehicle = vehicleService.findById(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = usersService.findByUsername(username);

        Customer customer = customerService.getOne(user.getCustomerId());

        customerService.saveCustomer(customer);

        deal.setCustomerId(user.getCustomerId());
        deal.setVehicleId(id);
        deal.setStatus("0");
        deal.setPrice(vehicle.get().getPrice());
        deal.setOrderDate(Date.valueOf(localDate));

        dealService.save(deal);
        return "redirect:/index";
    }

    @GetMapping(path = "/order-list/{id}")
    public String listOrder(@PathVariable Long id,
                            Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = usersService.findByUsername(username);
        Optional<Customer> customer = customerService.findCustomerById(user.getCustomerId());

        List<Deal> list = dealService.findAllByCustomerId(id);


        model.addAttribute("deal", list);
        model.addAttribute("user", user);
        model.addAttribute("customer", customer.get());
        return "order-list";
    }

}
