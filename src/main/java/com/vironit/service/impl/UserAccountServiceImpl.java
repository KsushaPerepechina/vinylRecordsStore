package com.vironit.vinylRecordsStore.service.impl;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Cart;
import com.vironit.vinylRecordsStore.entity.Contacts;
import com.vironit.vinylRecordsStore.entity.Account;
import com.vironit.vinylRecordsStore.dto.UserDTO;
import com.vironit.vinylRecordsStore.service.UserAccountService;
import com.vironit.vinylRecordsStore.dao.UserAccountDAO;
import com.vironit.vinylRecordsStore.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса аккаунта пользователя.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDAO userAccountDAO;
    
    @Autowired
    @Qualifier(value = "authenticationManager")
    private AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public void save(Account account) {
        userAccountDAO.save(account);
    }

    @Transactional
    @Override
    public void delete(Account account) {
        userAccountDAO.delete(account);
    }

    @Transactional(readOnly = true)
    @Override
    public Account findOne(long accountId) {
        return userAccountDAO.findOne(accountId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> findAll() {
        return userAccountDAO.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Account findByEmail(String email) {
        return userAccountDAO.findByEmail(email);
    }
    
    //--------------------------------------- Операции с аккаунтом пользователя
    
    @Transactional(readOnly = true)
    @Override
    public Account getUserAccount(String userLogin) {
        return userAccountDAO.findByEmail(userLogin);
    }

    @Transactional
    @Override
    public Account createUserThenAuthenticate(UserDTO user) throws EmailExistsException {
        Account account = createUserAccount(user);
        account.setCart(new Cart(account));
        userAccountDAO.save(account);
        authenticateUser(user.getEmail(), user.getPassword());
        return account;
    }

    private Account createUserAccount(UserDTO user) throws EmailExistsException {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        String hashedPassword = pe.encode(user.getPassword());
        if (findByEmail(user.getEmail()) != null) {
            throw new EmailExistsException();
        }
        Account account = new Account(user.getEmail(), hashedPassword, user.getName(), true);
        Contacts contacts = new Contacts(account, user.getPhone(), user.getAddress());
        account.setContacts(contacts);
        return account;
    }
    
    private void authenticateUser(String email, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (BadCredentialsException ex) {
        }
    }
}
