package com.vironit.vinylRecordsStore.dto.assembler;

import com.vironit.vinylRecordsStore.dto.ProductPreviewDTO;
import com.vironit.vinylRecordsStore.entity.Product;
import com.vironit.vinylRecordsStore.rest.ProductsRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 *
 */
public class ProductPreviewAssembler extends ResourceAssemblerSupport<Product, ProductPreviewDTO> {

    public ProductPreviewAssembler() {
        super(ProductsRestController.class, ProductPreviewDTO.class);
    }

    @Override
    public ProductPreviewDTO toResource(Product product) {
        ProductPreviewDTO dto = createResourceWithId(product.getId(), product);
        dto.setProductId(product.getId());
        dto.setDistillery(product.getStyle().getTitle());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
