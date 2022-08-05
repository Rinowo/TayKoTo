package com.example.cardealer.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Customer Visit", schema = "dbo", catalog = "Car Dealer")
public class CustomerVisit {
    private long customerVisitId;
    private Long customerId;
    private Long vehicleId;
    private Short testDrive;
    private Date visitDate;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Customer_Visit_Id")
    public long getCustomerVisitId() {
        return customerVisitId;
    }

    public void setCustomerVisitId(long customerVisitId) {
        this.customerVisitId = customerVisitId;
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
    @Column(name = "Test_Drive")
    public Short getTestDrive() {
        return testDrive;
    }

    public void setTestDrive(Short testDrive) {
        this.testDrive = testDrive;
    }

    @Basic
    @Column(name = "Visit_Date")
    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerVisit that = (CustomerVisit) o;
        return customerVisitId == that.customerVisitId && Objects.equals(customerId, that.customerId) && Objects.equals(vehicleId, that.vehicleId) && Objects.equals(testDrive, that.testDrive) && Objects.equals(visitDate, that.visitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerVisitId, customerId, vehicleId, testDrive, visitDate);
    }
}
