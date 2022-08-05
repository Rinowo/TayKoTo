package com.example.cardealer.controller;

import com.example.cardealer.dto.ListCategory;
import com.example.cardealer.dto.ListInfoService;
import com.example.cardealer.dto.ListVehicle;
import com.example.cardealer.model.InfoService;
import com.example.cardealer.model.Vehicle;
import com.example.cardealer.model.VehicleCategory;
import com.example.cardealer.service.InfoServiceImpl;
import com.example.cardealer.service.VehicleCategoryServiceImpl;
import com.example.cardealer.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    VehicleServiceImpl vehicleService;
    @Autowired
    InfoServiceImpl infoService;

    @Autowired
    VehicleCategoryServiceImpl vehicleCategoryService;


    //Vehicle Controller
    @GetMapping(value = "/getAll")
    public ResponseEntity<ListVehicle> listVehicle() {
        List<Vehicle> list = vehicleService.getAll();
        ListVehicle vh = new ListVehicle();
        vh.setData(list);
        return new ResponseEntity<>(vh, HttpStatus.OK);
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") Long id,
                                                 @RequestBody Vehicle vehicle) {
        Vehicle oldVehicle = vehicleService.findById(id).get();
        if (oldVehicle == null) {
            return ResponseEntity.notFound().build();
        } else {
            oldVehicle.setVehicleMake(vehicle.getVehicleMake());
            oldVehicle.setVehicleModel(vehicle.getVehicleModel());
            oldVehicle.setVehicleType(vehicle.getVehicleType());
            oldVehicle.setVehicleYear(vehicle.getVehicleYear());
            oldVehicle.setVehicleEngine(vehicle.getVehicleEngine());
            oldVehicle.setVehicleHorsepower(vehicle.getVehicleHorsepower());
            oldVehicle.setBodyStyle(vehicle.getBodyStyle());
            oldVehicle.setStatus(vehicle.getStatus());
            oldVehicle.setPrice(vehicle.getPrice());
            oldVehicle.setImg(vehicle.getImg());
            return ResponseEntity.ok(oldVehicle);
        }
    }

    //End

    //Info Service Controller
    @GetMapping(value = "/getAllInfo")
    public ResponseEntity<ListInfoService> listInfoService() {
        List<InfoService> list = infoService.getAll();
        ListInfoService lis = new ListInfoService();
        lis.setData(list);
        return new ResponseEntity<>(lis, HttpStatus.OK);
    }


    //Category Controller
    @GetMapping(value = "/getCategory")
    public ResponseEntity<ListCategory> listCategory() {
        List<VehicleCategory> list = vehicleCategoryService.getAll();
        ListCategory lc = new ListCategory();
        lc.setData(list);
        return new ResponseEntity<>(lc, HttpStatus.OK);
    }


}
