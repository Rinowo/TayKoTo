package com.example.cardealer.controller;


import com.example.cardealer.dto.OrderDto;
import com.example.cardealer.model.*;
import com.example.cardealer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @Autowired
    private InfoServiceImpl infoService;

    @Autowired
    private VehicleCategoryServiceImpl vehicleCategoryService;

    @Autowired
    private GalleryServiceImpl galleryService;

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private DealServiceImpl dealService;

    private static final String UPLOADED_FOLDER = "src/main/resources/static/img/";


    @GetMapping(value = {"/", "/index", "/home"})
    public String showIndex(Model model,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "6") int limit) {
        Page<Vehicle> pagination = vehicleService.findVehicle(PageRequest.of(page - 1, limit));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = usersService.findByUsername(username);

        model.addAttribute("listVehicle", pagination);
        model.addAttribute("page", page);
        model.addAttribute("limit", limit);
        model.addAttribute("datetime", Calendar.getInstance().getTime());
        model.addAttribute("user", user);
        return "index-guest";
    }

    @GetMapping(value = "/admin")
    public String showIndexAdmin(Model model,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "6") int limit) {
        Page<Vehicle> pagination = vehicleService.findVehicle(PageRequest.of(page - 1, limit));
        model.addAttribute("listVehicle", pagination);
        model.addAttribute("page", page);
        model.addAttribute("limit", limit);
        model.addAttribute("datetime", Calendar.getInstance().getTime());
        return "index-admin";
    }

    @GetMapping(value = {"/cars"})
    public String showCarGuest(Model model) {
        model.addAttribute("listVehicle", vehicleService.getAll());
        return "cars-guest";
    }

    @GetMapping(value = {"/cars-admin"})
    public String showCarsAdmin(Model model) {
        model.addAttribute("listVehicle", vehicleService.getAll());
        return "cars-admin";
    }

    @GetMapping(value = {"/cars-management"})
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showCarsManagement(Model model) {
        //model.addAttribute("category", vehicleCategoryService.getAll());
        model.addAttribute("listVehicle", vehicleService.getAll());
        //model.addAttribute("vehicle", new Vehicle());
        return "cars-management";
    }

    @RequestMapping(path = "/vehicle/find/{id}", method = RequestMethod.GET)
    public String editVehicle(@PathVariable Long id,
                              Model model) {
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if (vehicle.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
            return "vehicle-form";
        } else {
            return "not-found";
        }
    }

    @RequestMapping(path = "/vehicle/finds/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable Long id,
                              Model model) {
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if (vehicle.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
            model.addAttribute("category", vehicleCategoryService.getAll());
            return "update-vehicle";
        } else {
            return "not-found";
        }
    }
//    @RequestMapping(path = "/customer/finds/{id}", method = RequestMethod.GET)
//    public String editCustomer(@PathVariable Long id,
//                              Model model) {
//        Optional<Customer> customer = customerService.findCustomerById(id);
//        if (customer.isPresent()) {
//            model.addAttribute("vehicle", customer.get());
//            return "update-vehicle";
//        } else {
//            return "not-found";
//        }
//    }

    @RequestMapping(path = "/car/{id}/m", method = RequestMethod.GET)
    public String detailVehicleManager(@PathVariable Long id,
                                        Model model) {
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        Optional<InfoService> info = infoService.findByVehicleId(id);
        List<VehicleGallery> gallery = galleryService.getAllByVehicleId(id);

        if (info.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
            model.addAttribute("info", info.get());
            model.addAttribute("gallery", gallery);
            return "car-detail-manager";
        } else {
            return "not-found";
        }
    }

    @RequestMapping(path = "/image/find/{id}", method = RequestMethod.GET)
    public String editImage(@PathVariable Long id,
                              Model model) {
        Optional<VehicleGallery> gallery = galleryService.findByVehicleId(id);
        if (gallery.isPresent()) {
            model.addAttribute("gallery", gallery.get());
            return "images-form";
        } else {
            return "not-found";
        }
    }

    @GetMapping(path = "/add/images")
    public String imagesForm(@ModelAttribute Vehicle vehicle,
                                Model model) {

        return "images-form";
    }

//    @GetMapping(path = "/add/images")
//    public String showImageForm(@PathVariable("id") long id, Model model) {
//        VehicleGallery gallery = galleryService.findByVehicleId(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//
//        model.addAttribute("gallery", gallery);
//        return "images-form";
//    }

    @PostMapping(path = "/add/images")
    public String saveGallery(@Valid VehicleGallery gallery,
                              BindingResult result,
                              @RequestParam("img") MultipartFile myFile) {
        gallery.setImg("_");
//        if (result.hasErrors()) {
//            return "vehicle-form";
//        }
        try {
            Path path = Paths.get(UPLOADED_FOLDER + myFile.getOriginalFilename());
            Files.write(path, myFile.getBytes());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        gallery.setImg("/img/" + myFile.getOriginalFilename());
        galleryService.saveImg(gallery);

        return "redirect: /car-detail-manager/" + gallery.getVehicleId();
    }

    @GetMapping(path = "/add/vehicle")
    public String createVehicle(@ModelAttribute Vehicle vehicle,
                                Model model) {
   //     model.addAttribute("category", vehicleCategoryService.getAll());
        return "vehicle-form";
    }


    @PostMapping(path = "/add/vehicle")
    public String saveVehicle(@Valid Vehicle vehicle,
                              BindingResult result,
                              @RequestParam("img") MultipartFile myFile) {
        vehicle.setImg("_");
//        if (result.hasErrors()) {
//            return "vehicle-form";
//        }
        try {
            Path path = Paths.get(UPLOADED_FOLDER + myFile.getOriginalFilename());
            Files.write(path, myFile.getBytes());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        vehicle.setImg("/img/" + myFile.getOriginalFilename());
        vehicleService.saveVehicle(vehicle);

        InfoService info = new InfoService();

        info.setVehicleId(vehicle.getVehicleId());
        info.setVehicleMake(vehicle.getVehicleMake());
        info.setVehicleModel(vehicle.getVehicleModel());
        info.setBodyStyle(vehicle.getBodyStyle());
        info.setVehicleType(vehicle.getVehicleType());
        info.setVehicleYear(vehicle.getVehicleYear());
        info.setVehicleEngine(vehicle.getVehicleEngine());
        info.setVehicleHorsepower(vehicle.getVehicleHorsepower());
        info.setPrice(vehicle.getPrice());
        infoService.saveInfo(info);

        return "redirect:/cars-management";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Vehicle vehicle = vehicleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("vehicle", vehicle);
        return "update-vehicle";
    }

    @RequestMapping(value = { "/update/{id}" },
            method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public String updateVehicle(@PathVariable("id") Long id,
                             @Valid Vehicle vehicle,
                             BindingResult result,
                             @RequestParam("imgnew") MultipartFile myFile) {

        if (!myFile.getOriginalFilename().equals("")) {
            try {
                Path path = Paths.get(UPLOADED_FOLDER + myFile.getOriginalFilename());
                Files.write(path, myFile.getBytes());
                vehicle.setImg("/img/" + myFile.getOriginalFilename());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        InfoService info = infoService.getOne(id);


        vehicle.setVehicleId(id);
        vehicleService.saveVehicle(vehicle);

        info.setVehicleId(id);
        info.setVehicleMake(vehicle.getVehicleMake());
        info.setVehicleModel(vehicle.getVehicleModel());
        info.setBodyStyle(vehicle.getBodyStyle());
        info.setVehicleType(vehicle.getVehicleType());
        info.setVehicleYear(vehicle.getVehicleYear());
        info.setVehicleEngine(vehicle.getVehicleEngine());
        info.setVehicleHorsepower(vehicle.getVehicleHorsepower());
        info.setPrice(vehicle.getPrice());
        infoService.saveInfo(info);
        return "redirect:/cars-management";
    }

    @GetMapping("/delete/vehicle/{id}")
    public String deleteVehicle(@PathVariable("id") Long id,
                                Model model) {
        Vehicle vehicle = vehicleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle id: " + id));
        infoService.deleteInfo(id);
        vehicleService.deleteVehicleId(id);
        return "redirect:/cars-management";
    }

    @GetMapping("/403")
    public String getError() {
        return "403";
    }
}
