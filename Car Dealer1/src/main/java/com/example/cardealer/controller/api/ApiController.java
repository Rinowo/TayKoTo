package com.example.cardealer.controller.api;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
public class ApiController {
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

    @RequestMapping(path = "/vehicle/list", method = RequestMethod.GET)
    public String getListProduct(Model model, @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int limit) {
        Page<Vehicle> pagination = vehicleService.findVehicle(PageRequest.of(page - 1, limit));
        model.addAttribute("pagination", pagination);
        model.addAttribute("page", page);
        model.addAttribute("limit", limit);
        model.addAttribute("datetime", Calendar.getInstance().getTime());
        return "product-list";
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
