package com.vironit.vinylRecordsStore.dao;

import com.vironit.vinylRecordsStore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * ДАО корзины.
 */
public interface CartDAO extends CrudRepository<Cart, Long>, JpaRepository<Cart, Long>
{

}
