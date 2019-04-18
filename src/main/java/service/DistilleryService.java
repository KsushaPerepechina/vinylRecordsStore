package com.vironit.vinylRecordsStore.service;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Style;

/**
 * Сервис винокурни.
 */
public interface DistilleryService {

    void save(Style style);
    
    void delete(Style style);

    Style findOne(long distilleryId);

    List<Style> findByRegionOrderByTitle(Region region);

    List<Style> findAllOrderById();

    List<Style> findAllOrderByTitle();
}
