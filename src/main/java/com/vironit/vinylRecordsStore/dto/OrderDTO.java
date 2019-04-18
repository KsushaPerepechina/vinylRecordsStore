package com.vironit.vinylRecordsStore.dto;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.hateoas.ResourceSupport;

/**
 * 
 */
public class OrderDTO extends ResourceSupport {
    
    private String user;
    private long orderId;
    private int billNumber;
    private LocalDate dateCreated;
    private double productsCost;
    private double deliveryCost;
    private boolean deliveryIncluded;
    private double totalCost;
    private boolean payed;
    private boolean executed;
    
    public OrderDTO() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public double getProductsCost() {
        return productsCost;
    }

    public void setProductsCost(double productsCost) {
        this.productsCost = productsCost;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isDeliveryIncluded() {
        return deliveryIncluded;
    }

    public void setDeliveryIncluded(boolean deliveryIncluded) {
        this.deliveryIncluded = deliveryIncluded;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
