package com.vironit.vinylRecordsStore.controller.frontend;

import com.vironit.vinylRecordsStore.entity.Style;
import com.vironit.vinylRecordsStore.entity.Product;
import com.vironit.vinylRecordsStore.service.StyleService;
import com.vironit.vinylRecordsStore.service.ProductService;
import com.vironit.vinylRecordsStore.sorting.ISorter;
import com.vironit.vinylRecordsStore.sorting.SortingValuesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер витрины. 
 */
@Controller
@RequestMapping("/styles")
public class ShowcaseController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private StyleService styleService;
    
    @Autowired
    private ISorter<Product> productSorting;
    
    /**
     * Страница товаров стиля. Фильтрация и сортировка.
     *
     * @param styleId идентификатор стиля
     * @param sortingValues параметры сортировки
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{styleId}")
    public String getRegionProducts(
            @PathVariable long styleId,
            SortingValuesDTO sortingValues,
            @RequestParam Model model
    ) {
        Style style = styleService.findOne(styleId);
        PageRequest request = productSorting.updateSorting(sortingValues);
        Page<Product> pagedList;
        pagedList = productService.findByStyle(style, request);
        model.addAttribute("currentStyleTitle", style.getTitle());
        productSorting.prepareModel(model, pagedList);
        model.addAttribute("selectedStyle", style);
        model.addAttribute("styles", styleService.findAllOrderByTitle());
        return "styles";
    }
}
