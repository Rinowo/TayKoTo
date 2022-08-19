package com.example.cardealer.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Vehicle Order", schema = "dbo", catalog = "Car Dealer")
public class VehicleOrder {
    private long orderId;
    private Long vehicleId;
    private Date orderDate;
    private Date arriveDate;
    private String status;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Order_Id")
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "Vehicle_Id")
    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Basic
    @Column(name = "Order_Date")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "Arrive_Date")
    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    @Basic
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleOrder that = (VehicleOrder) o;
        return orderId == that.orderId && Objects.equals(vehicleId, that.vehicleId) && Objects.equals(orderDate, that.orderDate) && Objects.equals(arriveDate, that.arriveDate) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, vehicleId, orderDate, arriveDate, status);
    }
}
