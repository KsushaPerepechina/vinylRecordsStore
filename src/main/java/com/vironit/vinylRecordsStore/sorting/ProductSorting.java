package com.vironit.vinylRecordsStore.sorting;

import org.springframework.stereotype.Component;

/**
 * Опции сортировки и фильтрации списка товаров.
 */
@Component
public class ProductSorting extends AbstractSorter {

    {
        sortFieldOptions.put("price", "по цене");
        sortFieldOptions.put("style.title", "по стилю");
        sortFieldOptions.put("year", "по году");
    }
}
