package com.example.cardealer.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Deal {
    private long dealId;
    private Long customerId;
    private Long vehicleId;
    private String status;
    private Double price;
    private Double deposit;
    private Date orderDate;
    private Date arriveDate;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Deal_Id")
    public long getDealId() {
        return dealId;
    }

    public void setDealId(long dealId) {
        this.dealId = dealId;
    }

    @Basic
    @Column(name = "Customer_Id")
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

//    @Basic
//    @Column(name = "Vehicle_Id")
//    public Long getVehicleId() {
//        return vehicleId;
//    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Vehicle_Id")
    private Vehicle vehicle;

    @Basic
    @Column(name = "Vehicle_Id")
    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Basic
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "Price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Deposit")
    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    @Basic
    @Column(name = "Order_Date")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    @Temporal(TemporalType.DATE)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "Arrive_Date")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    @Temporal(TemporalType.DATE)
    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deal deal = (Deal) o;
        return dealId == deal.dealId &&
                Objects.equals(customerId, deal.customerId) &&
                Objects.equals(vehicleId, deal.vehicleId) &&
                Objects.equals(status, deal.status) &&
                Objects.equals(price, deal.price) &&
                Objects.equals(deposit, deal.deposit) &&
                Objects.equals(orderDate, deal.orderDate) &&
                Objects.equals(arriveDate, deal.arriveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealId, customerId, vehicleId, status, price, deposit, orderDate, arriveDate);
    }


}
