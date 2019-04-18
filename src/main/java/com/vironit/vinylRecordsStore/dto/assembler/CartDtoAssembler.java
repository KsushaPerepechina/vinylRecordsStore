package com.vironit.vinylRecordsStore.dto.assembler;

import java.util.List;

import com.vironit.vinylRecordsStore.dto.CartDTO;
import com.vironit.vinylRecordsStore.dto.CartItemDTO;
import com.vironit.vinylRecordsStore.rest.ContactsRestController;
import com.vironit.vinylRecordsStore.entity.Cart;
import com.vironit.vinylRecordsStore.rest.CartRestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class CartDtoAssembler extends ResourceAssemblerSupport<Cart, CartDTO> {

    public CartDtoAssembler() {
        super(CartRestController.class, CartDTO.class);
    }

    @Override
    public CartDTO toResource(Cart cart) {
        return instantiateResource(cart);
    }
        
    public CartDTO toUserResource(Cart cart, double deliveryCost) {
        CartDTO dto = toAnonymousResource(cart, deliveryCost);
        dto.setUser(cart.getUserAccount().getEmail());
        dto.add(linkTo(ContactsRestController.class).withRel("Customer contacts"));
        dto.add(linkTo(CartRestController.class).slash("payment").withRel("Payment"));
        return dto;
    }
    
    public CartDTO toAnonymousResource(Cart cart, double deliveryCost) {
        double currentDeliveryCost = cart.isDeliveryIncluded() ? deliveryCost : 0;
        double totalCost = cart.isEmpty() ? 0 : (cart.getProductsCost() + currentDeliveryCost);
        
        CartDTO dto = toResource(cart);
        dto.setDeliveryIncluded(cart.isDeliveryIncluded());
        dto.setDeliveryCost(deliveryCost);
        dto.setProductsCost(cart.getProductsCost());
        dto.setTotalCost(totalCost);
        dto.setTotalItems(cart.getTotalItems());
        
        List<CartItemDTO> items = new CartItemDtoAssembler().toResources(cart.getCartItems());
        dto.setItems(items);
        
        return dto;
    }
}
