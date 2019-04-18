package com.vironit.vinylRecordsStore.rest;

import javax.validation.Valid;

import com.vironit.vinylRecordsStore.dto.UserDTO;
import com.vironit.vinylRecordsStore.dto.assembler.UserAccountDtoAssembler;
import com.vironit.vinylRecordsStore.entity.Account;
import com.vironit.vinylRecordsStore.exception.EmailExistsException;
import com.vironit.vinylRecordsStore.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST-контроллер регистрации покупателя.
 */
@Controller
@RequestMapping(value = "/rest/signup")
public class SignupRestController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountDtoAssembler userAccountDtoAssembler;

    /**
     * Регистрация нового покупателя.
     * 
     * @param user данные нового покупателя
     * @return вновь зарегистрированный покупатель
     * @throws EmailExistsException если покупатель с таким адресом уже существует
     */
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaUtf8.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaUtf8.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UserDTO postNewUser(@Valid @RequestBody UserDTO user) throws EmailExistsException {
        Account account = userAccountService.createUserThenAuthenticate(user);
        return userAccountDtoAssembler.toResource(account);
    }
}
