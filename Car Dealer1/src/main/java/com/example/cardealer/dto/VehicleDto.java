package com.example.cardealer.dto;

public class VehicleDto {
    private Long vehicleId;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleType;
    private Integer vehicleYear;
    private String vehicleEngine;
    private String vehicleHorsepower;
    private String bodyStyle;
    private Long price;

    public VehicleDto() {
    }

    public VehicleDto(Long vehicleId, String vehicleMake, String vehicleModel, String vehicleType, Integer vehicleYear, String vehicleEngine, String vehicleHorsepower, String bodyStyle, Long price) {
        this.vehicleId = vehicleId;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.vehicleYear = vehicleYear;
        this.vehicleEngine = vehicleEngine;
        this.vehicleHorsepower = vehicleHorsepower;
        this.bodyStyle = bodyStyle;
        this.price = price;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleEngine() {
        return vehicleEngine;
    }

    public void setVehicleEngine(String vehicleEngine) {
        this.vehicleEngine = vehicleEngine;
    }

    public String getVehicleHorsepower() {
        return vehicleHorsepower;
    }

    public void setVehicleHorsepower(String vehicleHorsepower) {
        this.vehicleHorsepower = vehicleHorsepower;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
