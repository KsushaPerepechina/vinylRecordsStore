package com.vironit.vinylRecordsStore.controller.backend;

import javax.validation.Valid;

import com.vironit.vinylRecordsStore.entity.Style;
import com.vironit.vinylRecordsStore.entity.Product;
import com.vironit.vinylRecordsStore.entity.Storage;
import com.vironit.vinylRecordsStore.exception.ProductNotFoundException;
import com.vironit.vinylRecordsStore.service.StyleService;
import com.vironit.vinylRecordsStore.service.ProductService;
import com.vironit.vinylRecordsStore.sorting.ISorter;
import com.vironit.vinylRecordsStore.sorting.SortingValuesDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер управления товарами.
 */
@Controller
@RequestMapping("/admin/products")
@Secured({"ROLE_STAFF", "ROLE_ADMIN"})
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private StyleService styleService;
    
    @Autowired
    private ISorter<Product> productBackendSorting;

    /**
     * Перечень товаров.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getProducts(
            SortingValuesDTO sortingValues,
            @RequestParam(value = "dist", required = false, defaultValue = "0") Long styleId,
            Model model
    ) {
        PageRequest request = productBackendSorting.updateSorting(sortingValues);
        Page<Product> pagedList;
        if (styleId == 0) {
            pagedList = productService.findAll(request);
        } else {
            Style style = styleService.findOne(styleId);
            pagedList = productService.findByStyle(style, request);
            model.addAttribute("currentStyleTitle", style.getTitle());
        }
        productBackendSorting.prepareModel(model, pagedList);
        
        model.addAttribute("styles", styleService.findAllOrderByTitle());
        return "admin/products";
    }

    //------------------------------------------------------- Добавление товара
    
    /**
     * Страница добавления.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("styles", styleService.findAllOrderByTitle());
        return "admin/products/new";
    }

    /**
     * Сохранение нового товара.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String postProduct(
            @Valid Product product,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/products/new";
        }
        Style style = styleService.findOne(product.getStyle().getId());
        product.setStyle(style);

        Storage available = new Storage(product);
        product.setStorage(available);

        productService.save(product);
        return "redirect:/admin/products";
    }

    //--------------------------------------------------- Редактирование товара
    
    /**
     * Страница редактирования.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{productId}/edit")
    public String editProduct(
            @PathVariable long productId,
            Model model
    ) throws ProductNotFoundException {
        model.addAttribute("product", productService.findOne(productId));
        model.addAttribute("styles", styleService.findAllOrderByTitle());
        return "admin/products/edit";
    }

    /**
     * Изменение товара.
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{productId}")
    public String putProduct(
            @PathVariable long productId,
            @Valid Product product,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/products/edit";
        }
        Style style = styleService.findOne(product.getStyle().getId());
        product.setStyle(style);
        productService.save(product);//!
        return "redirect:/admin/products";
    }

    //--------------------------------------------------------- Удаление товара
    
    /**
     * Удаление товара.
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{productId}")
    public String deleteProduct(@PathVariable long productId) throws ProductNotFoundException {
        Product product = productService.findOne(productId);
        productService.delete(product);
        return "redirect:/admin/products";
    }
}
