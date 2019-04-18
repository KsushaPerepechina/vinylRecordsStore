package com.vironit.vinylRecordsStore.service.impl;

import com.vironit.vinylRecordsStore.entity.Style;
import com.vironit.vinylRecordsStore.entity.Product;
import com.vironit.vinylRecordsStore.service.ProductService;
import com.vironit.vinylRecordsStore.dao.ProductDAO;
import java.util.List;

import com.vironit.vinylRecordsStore.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса товара.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    
    @Transactional
    @Override
    public void save(Product product) {
        productDAO.save(product);
    }

    @Transactional
    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Product findOne(long productId) throws ProductNotFoundException {
        Product product = productDAO.findOne(productId);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAllOrderById() {
        return productDAO.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> findAll(PageRequest request) {
        return productDAO.findAll(request);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Product> findByDistillery(Style style) {
        return productDAO.findByDistillery(style);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Page<Product> findByDistillery(Style style, Pageable pageable) {
        return productDAO.findByDistillery(style, pageable);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Page<Product> findByDistilleriesOfRegion(Region region, Pageable pageable) {
        return productDAO.findByDistilleriesOfRegion(region, pageable);
    }
}
