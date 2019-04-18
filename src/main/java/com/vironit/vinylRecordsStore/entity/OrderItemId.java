package com.vironit.vinylRecordsStore.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Первичный ключ товара в составе заказа.
 */
@Embeddable
public class OrderItemId implements Serializable {
    
    private Long orderId;
    private Long productId;
    
    public OrderItemId(){
    }
    
    public OrderItemId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public Long getCustomerOrder() {
        return orderId;
    }

    public void setCustomerOrder(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProduct() {
        return productId;
    }

    public void setProduct(Long productId) {
        this.productId = productId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        OrderItemId that = (OrderItemId) o;
 
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
 
        return true;
    }
 
    @Override
    public int hashCode() {
        int result;
        result = (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
    
}
