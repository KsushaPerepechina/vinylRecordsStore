package com.vironit.vinylRecordsStore.controller.frontend;

import com.vironit.vinylRecordsStore.service.StyleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.ui.Model;

/**
 * Контроллер пользовательского интерфейса.
 */
@Controller
@EnableEntityLinks
public class FrontendController {

    @Autowired
    private StyleService styleService;

    /**
     * Главная страница.
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("styles", styleService.findAllOrderByTitle());
        model.addAttribute("selectedStyle", styleService.findOne(1));
        return "index";
    }
    
    /**
     * Страница входа в магазин.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * Описание реализации магазина.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/inside")
    public String whatsInside() {
        return "inside";
    }

    /**
     * Описание веб-службы REST.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/rest-api")
    public String restApi() {
        return "rest";
    }
}
