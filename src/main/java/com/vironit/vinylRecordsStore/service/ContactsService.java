package com.vironit.vinylRecordsStore.service;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Contacts;
import com.vironit.vinylRecordsStore.dto.ContactsDTO;

/**
 * Сервис контактных данных пользователя.
 */
public interface ContactsService {
    
    Contacts save(Contacts contacts);
    
    void delete(Contacts contacts);

    Contacts findOne(long contactsId);

    List<Contacts> findAll();
    
    /**
     * Получение контактных данных пользователя.
     * 
     * @param userLogin логин покупателя
     * @return контактные данные
     */
        
    ContactsDTO getUserContacts(String userLogin);
    
    /**
     * Изменение контактных данных пользователя.
     *
     * @param userLogin логин покупателя
     * @param newContacts новые контактные данные
     * @return изменённые контактные данные
     */
    ContactsDTO updateUserContacts(String userLogin, ContactsDTO newContacts);
}
