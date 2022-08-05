package com.example.cardealer.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Deal {
    private long dealId;
    private Long customerId;
    private Long vehicleId;
    private Long salePrice;
    private Date dealDate;
    private String status;
    private Long paymentId;
    private Long shipmentId;

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

    @Basic
    @Column(name = "Vehicle_Id")
    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Basic
    @Column(name = "Sale_Price")
    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    @Basic
    @Column(name = "Deal_Date")
    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
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
    @Column(name = "Payment_Id")
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "Shipment_Id")
    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deal deal = (Deal) o;
        return dealId == deal.dealId && Objects.equals(customerId, deal.customerId) && Objects.equals(vehicleId, deal.vehicleId) && Objects.equals(salePrice, deal.salePrice) && Objects.equals(dealDate, deal.dealDate) && Objects.equals(status, deal.status) && Objects.equals(paymentId, deal.paymentId) && Objects.equals(shipmentId, deal.shipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealId, customerId, vehicleId, salePrice, dealDate, status, paymentId, shipmentId);
    }
}
