package com.vironit.vinylRecordsStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * ДАО региона.
 */
public interface RegionDAO extends CrudRepository<Region, Long>, JpaRepository<Region, Long>
{
}
