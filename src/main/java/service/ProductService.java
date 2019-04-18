package com.vironit.vinylRecordsStore.service;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Style;
import com.vironit.vinylRecordsStore.entity.Product;
import com.vironit.vinylRecordsStore.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Сервис товаров.
 */
public interface ProductService {
    
    void save(Product product);
    
    void delete(Product product);

    Product findOne(long productId) throws ProductNotFoundException;

    List<Product> findAllOrderById();
    
    Page<Product> findAll(PageRequest request);
    
    List<Product> findByDistillery(Style style);
    
    Page<Product> findByDistillery(Style style, Pageable pageable);
    
    Page<Product> findByDistilleriesOfRegion(Region region, Pageable pageable);
}
