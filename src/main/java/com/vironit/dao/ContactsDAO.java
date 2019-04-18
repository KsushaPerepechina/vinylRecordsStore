package com.vironit.vinylRecordsStore.dao;

import com.vironit.vinylRecordsStore.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * ДАО контактных данных покупателя.
 */
public interface ContactsDAO extends CrudRepository<Contacts, Long>, JpaRepository<Contacts, Long>
{
    
}
