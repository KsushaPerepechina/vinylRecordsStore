package com.vironit.vinylRecordsStore.dto;

import java.util.List;
import org.springframework.hateoas.ResourceSupport;

/**
 * Адаптер корзины.
 */
public class CartDTO extends ResourceSupport {
    
    private String user;
    private List<CartItemDTO> items;
    private int totalItems;
    private double productsCost;
    private double deliveryCost;
    private boolean deliveryIncluded;
    private double totalCost;

    public CartDTO() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public boolean isDeliveryIncluded() {
        return deliveryIncluded;
    }

    public void setDeliveryIncluded(boolean deliveryIncluded) {
        this.deliveryIncluded = deliveryIncluded;
    }

    public double getProductsCost() {
        return productsCost;
    }

    public void setProductsCost(double productsCost) {
        this.productsCost = productsCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
