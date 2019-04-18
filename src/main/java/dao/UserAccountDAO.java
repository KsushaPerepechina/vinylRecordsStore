package com.vironit.vinylRecordsStore.dao;

import com.vironit.vinylRecordsStore.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * ДАО аккаунта пользователя. 
 */
public interface UserAccountDAO extends CrudRepository<Account, Long>, JpaRepository<Account, Long>
{
    Account findByEmail(String email);
}
