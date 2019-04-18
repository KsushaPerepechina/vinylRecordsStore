package com.vironit.vinylRecordsStore.dao;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Style;
import com.vironit.vinylRecordsStore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * ДАО товара.
 */
public interface ProductDAO extends CrudRepository<Product, Long>, JpaRepository<Product, Long>
{
    List<Product> findByDistillery(Style style);
    
    Page<Product> findByDistillery(Style style, Pageable pageable);
    
    @Query(value = "SELECT p FROM Product p WHERE p.distillery IN "
            + "(SELECT d FROM Distillery d WHERE d.region = :region)")
    public Page<Product> findByDistilleriesOfRegion(@Param("region") Region region, Pageable pageable);
}
