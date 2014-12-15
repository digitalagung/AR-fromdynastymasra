package com.squarecode.yogyatour.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage(ModelMap modelMap) {
        LOGGER.info("request show home page");
        modelMap.addAttribute("message", "Welcome Test Spring Boot Thymeleaf");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(ModelMap modelMap) {
        LOGGER.info("request show login page");
        return "login";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String getDeniedPage(ModelMap modelMap) {
        LOGGER.info("request show denied page");
        modelMap.addAttribute("message", "Denied Page");
        return "denied";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboard(ModelMap modelMap) {
        LOGGER.info("request show dashboard page");
        modelMap.addAttribute("message", "test from controller");
        return "fragment/dashboard";
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String getAdminPage() {
        LOGGER.info("request show admin user page");
        return "fragment/user";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest() {
        LOGGER.info("request show admin user page");
        return "test";
    }
}
