package com.vironit.vinylRecordsStore.dao;

import com.vironit.vinylRecordsStore.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * ДАО стиля.
 */
public interface StyleDAO extends CrudRepository<Style, Long>, JpaRepository<Style, Long>
{

}
