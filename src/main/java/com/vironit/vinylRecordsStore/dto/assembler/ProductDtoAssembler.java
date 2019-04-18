package com.vironit.vinylRecordsStore.dto.assembler;

import com.vironit.vinylRecordsStore.dto.ProductDTO;
import com.vironit.vinylRecordsStore.entity.Product;
import com.vironit.vinylRecordsStore.rest.CartRestController;
import com.vironit.vinylRecordsStore.rest.ProductsRestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 *
 */
public class ProductDtoAssembler extends ResourceAssemblerSupport<Product, ProductDTO> {

    public ProductDtoAssembler() {
        super(ProductsRestController.class, ProductDTO.class);
    }

    @Override
    public ProductDTO toResource(Product product) {
        ProductDTO dto = createResourceWithId(product.getId(), product);
        dto.setProductId(product.getId());
        dto.setDistillery(product.getStyle().getTitle());
        dto.setName(product.getName());
        dto.setAge(product.getAge());
        dto.setAlcohol(product.getAlcohol());
        dto.setPrice(product.getPrice());
        dto.setVolume(product.getVolume());
        dto.setDescription(product.getDescription());
        dto.setInStock(product.getStorage().isAvailable());
        dto.add(linkTo(CartRestController.class).withRel("Cart"));
        return dto;
    }
}
