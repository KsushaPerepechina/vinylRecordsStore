package com.vironit.vinylRecordsStore.service;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.UserAccount;
import com.vironit.vinylRecordsStore.dto.UserDTO;
import com.vironit.vinylRecordsStore.exception.EmailExistsException;

/**
 * Сервис аккаунта пользователя.
 */
public interface UserAccountService {

    void save(UserAccount userAccount);
    
    void delete(UserAccount userAccount);

    UserAccount findOne(long accountId);

    List<UserAccount> findAll();

    UserAccount findByEmail(String email);

    /**
     * Создание нового аккаунта с последующей авторизацией.
     *
     * @param user данные нового пользователя
     * @return вновь созданный аккаунт
     * @throws EmailExistsException если пользователь с таким адресом уже существует
     */
    UserAccount createUserThenAuthenticate(UserDTO user) throws EmailExistsException;
    
    /**
     * Получение аккаунта пользователя.
     *
     * @param userLogin логин пользователя
     * @return аккаунт пользователя с запрошенным логином
     */
    UserAccount getUserAccount(String userLogin);
}
