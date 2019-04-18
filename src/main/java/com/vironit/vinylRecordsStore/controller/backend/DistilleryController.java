package com.vironit.vinylRecordsStore.controller.backend;

import javax.validation.Valid;

import com.vironit.vinylRecordsStore.entity.Style;
import com.vironit.vinylRecordsStore.service.DistilleryService;
import com.vironit.vinylRecordsStore.service.RegionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Контроллер управления винокурнями.
 */
@Controller
@RequestMapping("/admin/distilleries")
@Secured({"ROLE_STAFF", "ROLE_ADMIN"})
public class DistilleryController {

    @Autowired
    private DistilleryService distilleryService;
    
    @Autowired
    private RegionService regionService;

    /**
     * Перечень винокурен.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String distillery(Model model) {
        model.addAttribute("distilleries", distilleryService.findAllOrderByTitle());
        return "admin/distilleries";
    }

    //------------------------------------------------ Создание новой категории
    
    /**
     * Страница добавления.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String newDistillery(Model model) {
        model.addAttribute("distillery", new Style());
        model.addAttribute("regions", regionService.findAllOrderByName());
        return "admin/distilleries/new";
    }

    /**
     * Сохранение новой винокурни.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String postDistillery(
            @Valid Style style,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/distilleries/new";
        }
        Region region = regionService.findOne(style.getRegion().getId());
        style.setRegion(region);
        distilleryService.save(style);
        return "redirect:/admin/distilleries";
    }

    //------------------------------------------------ Редактирование категории
    
    /**
     * Страница редактирования.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{distilleryId}/edit")
    public String editDistillery(
            @PathVariable long distilleryId,
            Model model
    ) {
        model.addAttribute("distillery", distilleryService.findOne(distilleryId));
        model.addAttribute("regions", regionService.findAllOrderByName());
        return "admin/distilleries/edit";
    }

    /**
     * Изменение винокурни.
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{distilleryId}")
    public String putDistillery(
            @PathVariable long distilleryId,
            @Valid Style style,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/distilleries/edit";
        }
        Region region = regionService.findOne(style.getRegion().getId());
        style.setRegion(region);
        distilleryService.save(style);//!
        return "redirect:/admin/distilleries";
    }

    //------------------------------------------------------ Удаление категории
    
    /**
     * Удаление винокурни.
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{distilleryId}")
    public String deleteDistillery(
            @PathVariable long distilleryId
    ) {
        Style style = distilleryService.findOne(distilleryId);
        distilleryService.delete(style);
        return "redirect:/admin/distilleries";
    }
}
