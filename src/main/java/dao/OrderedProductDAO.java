package com.vironit.vinylRecordsStore.dao;

import com.vironit.vinylRecordsStore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 */
public interface OrderedProductDAO extends CrudRepository<OrderItem, Long>, JpaRepository<OrderItem, Long>
{
    
}
