package com.vironit.vinylRecordsStore.dao;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * ДАО винокурни. 
 */
public interface DistilleryDAO extends CrudRepository<Style, Long>, JpaRepository<Style, Long>
{
    List<Style> findByRegionOrderByTitleAsc(Region region);
}
