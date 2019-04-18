package com.vironit.vinylRecordsStore.service.impl;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Cart;
import com.vironit.vinylRecordsStore.entity.Contacts;
import com.vironit.vinylRecordsStore.entity.UserAccount;
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
    public void save(UserAccount userAccount) {
        userAccountDAO.save(userAccount);
    }

    @Transactional
    @Override
    public void delete(UserAccount userAccount) {
        userAccountDAO.delete(userAccount);
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccount findOne(long accountId) {
        return userAccountDAO.findById(accountId).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserAccount> findAll() {
        return userAccountDAO.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccount findByEmail(String email) {
        return userAccountDAO.findByEmail(email);
    }
    
    @Transactional(readOnly = true)
    @Override
    public UserAccount getUserAccount(String userLogin) {
        return userAccountDAO.findByEmail(userLogin);
    }

    @Transactional
    @Override
    public UserAccount createUserThenAuthenticate(UserDTO user) throws EmailExistsException {
        UserAccount userAccount = createUserAccount(user);
        userAccount.setCart(new Cart(userAccount));
        userAccountDAO.save(userAccount);
        authenticateUser(user.getEmail(), user.getPassword());
        return userAccount;
    }

    private UserAccount createUserAccount(UserDTO user) throws EmailExistsException {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        String hashedPassword = pe.encode(user.getPassword());
        if (findByEmail(user.getEmail()) != null) {
            throw new EmailExistsException();
        }
        UserAccount userAccount = new UserAccount(user.getEmail(), hashedPassword, user.getName(), true);
        Contacts contacts = new Contacts(userAccount, user.getPhone(), user.getAddress());
        userAccount.setContacts(contacts);
        return userAccount;
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
