package com.vironit.vinylRecordsStore.service.impl;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Style;
import com.vironit.vinylRecordsStore.service.DistilleryService;
import com.vironit.vinylRecordsStore.dao.DistilleryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса винокурни.
 */
@Service
public class DistilleryServiceImpl implements DistilleryService {

    private final DistilleryDAO distilleryDAO;

    @Autowired
    public DistilleryServiceImpl(DistilleryDAO distilleryDAO) {
        this.distilleryDAO = distilleryDAO;
    }

    @Transactional
    @Override
    public void save(Style style) {
        distilleryDAO.save(style);
    }

    @Transactional
    @Override
    public void delete(Style style) {
        if (style != null) {
            distilleryDAO.delete(style);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Style findOne(long distilleryId) {
        return distilleryDAO.findOne(distilleryId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Style> findAllOrderById() {
        return distilleryDAO.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Style> findAllOrderByTitle() {
        return distilleryDAO.findAll(new Sort(Sort.Direction.ASC, "title"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Style> findByRegionOrderByTitle(Region region) {
        return distilleryDAO.findByRegionOrderByTitleAsc(region);
    }
}
