package com.vironit.vinylRecordsStore.dto.assembler;

import com.vironit.vinylRecordsStore.dto.ContactsDTO;
import com.vironit.vinylRecordsStore.entity.Contacts;
import com.vironit.vinylRecordsStore.rest.CartRestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ContactsDtoAssembler extends ResourceAssemblerSupport<Contacts, ContactsDTO> {

    public ContactsDtoAssembler() {
        super(CartRestController.class, ContactsDTO.class);
    }

    @Override
    public ContactsDTO toResource(Contacts contacts) {
        ContactsDTO dto = instantiateResource(contacts);
        dto.setPhone(contacts.getPhone());
        dto.setAddress(contacts.getAddress());
        dto.add(linkTo(CartRestController.class).withRel("Shopping cart"));
        return dto;
    }
}
