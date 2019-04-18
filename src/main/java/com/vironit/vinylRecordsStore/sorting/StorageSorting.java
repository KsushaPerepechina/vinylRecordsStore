package com.vironit.vinylRecordsStore.sorting;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * Опции сортировки и фильтрации списка единиц хранения.
 */
@Component
public class StorageSorting extends AbstractSorter {

    private final Map<String, String> availableOptions = new LinkedHashMap<>();

    {
        sortFieldOptions.put("price", "по цене");
        sortFieldOptions.put("style.title", "по стилю");
        sortFieldOptions.put("year", "по году");
        
        availableOptions.put("all", "все товары");
        availableOptions.put("true", "только в наличии");
        availableOptions.put("false", "только отсутствующие");
    }
    
    @Override
    public int getDefaultPageSize() {
        return 10;
    }

    @Override
    public Model prepareFilteredModel(Model model) {
        model.addAttribute("availableOptions", availableOptions);
        return model;
    }
}
