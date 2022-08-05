package com.example.cardealer.controller.api;


import com.example.cardealer.model.Vehicle;
import com.example.cardealer.service.InfoServiceImpl;
import com.example.cardealer.service.VehicleCategoryServiceImpl;
import com.example.cardealer.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class ApiController {

    @Autowired
    VehicleServiceImpl vehicleService;

    @Autowired
    InfoServiceImpl infoService;

    @Autowired
    VehicleCategoryServiceImpl vehicleCategoryService;

    private static final String UPLOADED_FOLDER = "target/classes/static/uploaded/";


    @GetMapping(value = {"/", "/index", "/home"})
    public String showIndex(Model model) {
        model.addAttribute("listVehicle", vehicleService.getAll());
        return "index-guest";
    }

    @GetMapping(value = "/admin")
    public String showIndexAdmin(Model model) {
        model.addAttribute("listVehicle", vehicleService.getAll());
        return "index-admin";
    }

    @GetMapping(value = "/customer")
    public String showIndexCustomer(Model model) {
        model.addAttribute("listVehicle", vehicleService.getAll());
        return "index-customer";
    }

    @GetMapping(value = {"/cars"})
    public String showCarGuest(Model model) {
        model.addAttribute("listVehicle", vehicleService.getAll());
        return "cars-guest";
    }

    @GetMapping(value = {"/cars-customer"})
    public String showCarsUser(Model model) {
        model.addAttribute("listVehicle", vehicleService.getAll());
        return "cars-customer";
    }

    @GetMapping(value = {"/cars-admin"})
    public String showCarsAdmin(Model model) {
        model.addAttribute("listVehicle", vehicleService.getAll());
        return "cars-admin";
    }

    @GetMapping(value = {"/cars-management"})
    public String showCarsManagement(Model model) {
        //model.addAttribute("category", vehicleCategoryService.getAll());
        model.addAttribute("listVehicle", vehicleService.getAll());
        //model.addAttribute("vehicle", new Vehicle());
        return "cars-management";
    }

//    @GetMapping(value = "/cars-management")
//    public String showCategory(Model model) {
//        model.addAttribute("category", vehicleCategoryService.getAll());
//        return "cars-management";
//    }

    @RequestMapping(path = "/vehicle/find/{id}", method = RequestMethod.GET)
    public String editVehicle(@PathVariable Long id, Model model) {
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if (vehicle.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
            return "vehicle-form";
        } else {
            return "not-found";
        }
    }

    @RequestMapping(path = "/vehicle/finds/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable Long id, Model model) {
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if (vehicle.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
            model.addAttribute("category", vehicleCategoryService.getAll());
            return "update-vehicle";
        } else {
            return "not-found";
        }
    }

    @GetMapping(path = "/add/vehicle")
    public String createVehicle(@ModelAttribute Vehicle vehicle) {
        return "vehicle-form";
    }


    @PostMapping(path = "/add/vehicle")
    public String saveVehicle(@Valid Vehicle vehicle, BindingResult result,
                              @RequestParam("img") MultipartFile myFile) {
        vehicle.setImg("_");
        try {
            Path path = Paths.get(UPLOADED_FOLDER + myFile.getOriginalFilename());
            Files.write(path, myFile.getBytes());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        vehicle.setImg("/uploaded/" + myFile.getOriginalFilename());
        vehicleService.saveVehicle(vehicle);
        return "redirect:/cars-management";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Vehicle vehicle = vehicleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("vehicle", vehicle);
        return "update-vehicle";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id,
                             @Valid Vehicle vehicle,
                             BindingResult result) {
        if (result.hasErrors()) {
            vehicle.setVehicleId(id);
            return "update-user";
        }
        vehicleService.saveVehicle(vehicle);
        return "redirect:/cars-management";
    }

//    public String updateVehicle(@Valid Vehicle vehicle, BindingResult result,
//                              @RequestParam("img") MultipartFile myFile) {
//        vehicle.setImg("_");
//        try {
//            Path path = Paths.get(UPLOADED_FOLDER + myFile.getOriginalFilename());
//            Files.write(path, myFile.getBytes());
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }
//        vehicle.setImg("/uploaded/" + myFile.getOriginalFilename());
//        vehicleService.saveVehicle(vehicle);
//        return "redirect:/cars-management";
//    }

    @GetMapping("/delete/vehicle/{id}")
    public String deleteVehicle(@PathVariable("id") Long id,
                                Model model) {
        Vehicle vehicle = vehicleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle id: " + id));
        vehicleService.deleteVehicleId(id);
        return "redirect:/cars-management";
    }

    @GetMapping("/cardetail/{id}")
    public String showDetail(@PathVariable("id") Long id,
                            Model model) {
        Vehicle vehicle = vehicleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle id: " + id));
        return "redirect:/cars-detail-guest";
    }

//    @GetMapping("/car-details/{id}")
//    public String detail(@PathVariable("id") Long id,
//                         Model model) {
//        model.addAttribute("info", infoService.getAll());
//        return "redirect:/cars-detail-guest";
//    }
}
