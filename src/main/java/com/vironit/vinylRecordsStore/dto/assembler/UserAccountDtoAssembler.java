package com.vironit.vinylRecordsStore.dto.assembler;

import com.vironit.vinylRecordsStore.dto.UserDTO;
import com.vironit.vinylRecordsStore.entity.Account;
import com.vironit.vinylRecordsStore.rest.CartRestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class UserAccountDtoAssembler extends ResourceAssemblerSupport<Account, UserDTO> {

    public UserAccountDtoAssembler() {
        super(CartRestController.class, UserDTO.class);
    }

    @Override
    public UserDTO toResource(Account account) {
        UserDTO dto = instantiateResource(account);
        dto.setEmail(account.getEmail());
        dto.setPassword("hidden");
        dto.setName(account.getName());
        dto.setPhone(account.getContacts().getPhone());
        dto.setAddress(account.getContacts().getAddress());
        dto.add(linkTo(CartRestController.class).withRel("Shopping cart"));
        return dto;
    }
}
