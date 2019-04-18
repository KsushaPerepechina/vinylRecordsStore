package com.vironit.vinylRecordsStore.rest;

import java.util.List;

import com.vironit.vinylRecordsStore.dto.ProductDTO;
import com.vironit.vinylRecordsStore.dto.ProductPreviewDTO;
import com.vironit.vinylRecordsStore.dto.assembler.ProductDtoAssembler;
import com.vironit.vinylRecordsStore.dto.assembler.ProductPreviewAssembler;
import com.vironit.vinylRecordsStore.entity.Product;
import com.vironit.vinylRecordsStore.exception.ProductNotFoundException;
import com.vironit.vinylRecordsStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST-контроллер товаров магазина.
 */
@Controller
@RequestMapping(value = "/rest/products")
@ExposesResourceFor(ProductDTO.class)
public class ProductsRestController {

    @Autowired
    private ProductService productService;
    
    /**
     * Просмотр всех товаров магазина.
     * 
     * @return список всех товаров
     */
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaUtf8.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ProductPreviewDTO> getProducts() {
        List<Product> products = productService.findAllOrderById();
        List<ProductPreviewDTO> dtos = new ProductPreviewAssembler().toResources(products);
        return dtos;
    }
    
    /**
     * Просмотр одного товара.
     * 
     * @param id идентификатор товара
     * @return запрошенный товар
     * @throws ProductNotFoundException если товар с запрошенным id не существует
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaUtf8.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ProductDTO getProduct(@PathVariable long id) throws ProductNotFoundException {
        Product product = productService.findOne(id);
        ProductDTO dto = new ProductDtoAssembler().toResource(product);
        return dto;
    }
}
