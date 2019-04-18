package com.vironit.vinylRecordsStore.service;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Account;
import com.vironit.vinylRecordsStore.dto.UserDTO;
import com.vironit.vinylRecordsStore.exception.EmailExistsException;

/**
 * Сервис аккаунта пользователя.
 */
public interface UserAccountService {

    void save(Account account);
    
    void delete(Account account);

    Account findOne(long accountId);

    List<Account> findAll();

    Account findByEmail(String email);
    
    //--------------------------------------- Операции с аккаунтом пользователя

    /**
     * Создание нового аккаунта с последующей авторизацией.
     *
     * @param user данные нового пользователя
     * @return вновь созданный аккаунт
     * @throws EmailExistsException если пользователь с таким адресом уже существует
     */
    Account createUserThenAuthenticate(UserDTO user) throws EmailExistsException;
    
    /**
     * Получение аккаунта пользователя.
     *
     * @param userLogin логин пользователя
     * @return аккаунт пользователя с запрошенным логином
     */
    Account getUserAccount(String userLogin);
}
