package com.vironit.vinylRecordsStore.dao;

import com.vironit.vinylRecordsStore.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * ДАО аккаунта пользователя. 
 */
public interface UserAccountDAO extends CrudRepository<UserAccount, Long>, JpaRepository<UserAccount, Long>
{
    UserAccount findByEmail(String email);
}
