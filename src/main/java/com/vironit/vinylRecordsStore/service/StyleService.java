package com.vironit.vinylRecordsStore.service;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Style;

/**
 * Сервис стиля.
 */
public interface StyleService {

    void save(Style style);
    
    void delete(Style style);

    Style findOne(long styleId);

    List<Style> findAllOrderById();

    List<Style> findAllOrderByTitle();
}
