package com.vironit.vinylRecordsStore.controller.backend;

import javax.validation.Valid;

import com.vironit.vinylRecordsStore.entity.Style;
import com.vironit.vinylRecordsStore.service.StyleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Контроллер управления стилями.
 */
@Controller
@RequestMapping("/admin/styles")
@Secured({"ROLE_STAFF", "ROLE_ADMIN"})
public class StyleController {

    @Autowired
    private StyleService styleService;

    /**
     * Перечень стилей.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String style(Model model) {
        model.addAttribute("styles", styleService.findAllOrderByTitle());
        return "admin/styles";
    }

    //------------------------------------------------ Создание новой категории
    
    /**
     * Страница добавления.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String newStyle(Model model) {
        model.addAttribute("style", new Style());
        return "admin/styles/new";
    }

    /**
     * Сохранение нового стиля.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String postStyle(
            @Valid Style style,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/styles/new";
        }
        styleService.save(style);
        return "redirect:/admin/styles";
    }

    //------------------------------------------------ Редактирование категории
    
    /**
     * Страница редактирования.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{styleId}/edit")
    public String editStyle(
            @PathVariable long styleId,
            Model model
    ) {
        model.addAttribute("style", styleService.findOne(styleId));
        return "admin/styles/edit";
    }

    /**
     * Изменение стиля.
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{styleId}")
    public String putStyle(
            @PathVariable long styleId,
            @Valid Style style,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/styles/edit";
        }
        styleService.save(style);//!
        return "redirect:/admin/styles";
    }

    //------------------------------------------------------ Удаление категории
    
    /**
     * Удаление стиля.
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{styleId}")
    public String deleteStyle(
            @PathVariable long styleId
    ) {
        Style style = styleService.findOne(styleId);
        styleService.delete(style);
        return "redirect:/admin/styles";
    }
}
