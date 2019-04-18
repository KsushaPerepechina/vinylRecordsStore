package com.vironit.vinylRecordsStore.dto.assembler;

import com.vironit.vinylRecordsStore.dto.CartItemDTO;
import com.vironit.vinylRecordsStore.entity.CartItem;
import com.vironit.vinylRecordsStore.rest.ProductsRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 *
 */
public class CartItemDtoAssembler extends ResourceAssemblerSupport<CartItem, CartItemDTO> {

    public CartItemDtoAssembler() {
        super(ProductsRestController.class, CartItemDTO.class);
    }

    @Override
    public CartItemDTO toResource(CartItem cartItem) {
        Long productId = cartItem.getProduct().getId();
        CartItemDTO dto = createResourceWithId(productId, cartItem);
        dto.setProductId(productId);
        dto.setQuantity((short)cartItem.getQuantity());
        return dto;
    }
}
