package com.vironit.vinylRecordsStore.dto.assembler;

import com.vironit.vinylRecordsStore.dto.UserDTO;
import com.vironit.vinylRecordsStore.entity.UserAccount;
import com.vironit.vinylRecordsStore.rest.CartRestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class UserAccountDtoAssembler extends ResourceAssemblerSupport<UserAccount, UserDTO> {

    public UserAccountDtoAssembler() {
        super(CartRestController.class, UserDTO.class);
    }

    @Override
    public UserDTO toResource(UserAccount userAccount) {
        UserDTO dto = instantiateResource(userAccount);
        dto.setEmail(userAccount.getEmail());
        dto.setPassword("hidden");
        dto.setName(userAccount.getName());
        dto.setPhone(userAccount.getContacts().getPhone());
        dto.setAddress(userAccount.getContacts().getAddress());
        dto.add(linkTo(CartRestController.class).withRel("Shopping cart"));
        return dto;
    }
}
