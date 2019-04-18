package com.vironit.vinylRecordsStore.service.impl;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Style;
import com.vironit.vinylRecordsStore.service.StyleService;
import com.vironit.vinylRecordsStore.dao.StyleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса стиля.
 */
@Service
public class StyleServiceImpl implements StyleService {

    private final StyleDAO styleDAO;

    @Autowired
    public StyleServiceImpl(StyleDAO styleDAO) {
        this.styleDAO = styleDAO;
    }

    @Transactional
    @Override
    public void save(Style style) {
        styleDAO.save(style);
    }

    @Transactional
    @Override
    public void delete(Style style) {
        if (style != null) {
            styleDAO.delete(style);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Style findOne(long styleId) {
        return styleDAO.findById(styleId).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Style> findAllOrderById() {
        return styleDAO.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Style> findAllOrderByTitle() {
        return styleDAO.findAll(new Sort(Sort.Direction.ASC, "title"));
    }

}
