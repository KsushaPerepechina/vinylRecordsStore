package com.vironit.vinylRecordsStore.service.impl;

import java.util.List;

import com.vironit.vinylRecordsStore.dao.ContactsDAO;
import com.vironit.vinylRecordsStore.entity.UserAccount;
import com.vironit.vinylRecordsStore.entity.Contacts;
import com.vironit.vinylRecordsStore.dto.ContactsDTO;
import com.vironit.vinylRecordsStore.dto.assembler.ContactsDtoAssembler;
import com.vironit.vinylRecordsStore.service.ContactsService;
import com.vironit.vinylRecordsStore.dao.UserAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса контактных данных пользователя.
 */
@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsDAO contactsDAO;
    
    @Autowired
    private UserAccountDAO userAccountDAO;
    
    @Autowired
    private ContactsDtoAssembler contactsDtoAssembler;

    @Transactional
    @Override
    public Contacts save(Contacts contacts) {
        return contactsDAO.save(contacts);
    }

    @Transactional
    @Override
    public void delete(Contacts contacts) {
        contactsDAO.delete(contacts);
    }

    @Transactional(readOnly = true)
    @Override
    public Contacts findOne(long contactsId) {
        return contactsDAO.findById(contactsId).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contacts> findAll() {
        return contactsDAO.findAll();
    }
    
    @Transactional(readOnly = true)
    @Override
    public ContactsDTO getUserContacts(String userLogin) {
        UserAccount userAccount = userAccountDAO.findByEmail(userLogin);
        Contacts contacts = userAccount.getContacts();
        return contactsDtoAssembler.toResource(contacts);
    }
    
    @Transactional
    @Override
    public ContactsDTO updateUserContacts(String userLogin, ContactsDTO newContacts) {
        UserAccount userAccount = userAccountDAO.findByEmail(userLogin);
        Contacts contacts = userAccount.getContacts();
        contacts.setPhone(newContacts.getPhone());
        contacts.setAddress(newContacts.getAddress());
        Contacts savedContacts = save(contacts);
        return contactsDtoAssembler.toResource(savedContacts);
    }
}
