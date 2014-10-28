package com.squarecode.yogyatour.controller;

import org.apache.log4j.Logger;
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
@RequestMapping("/")
public class MainController {
    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage(ModelMap modelMap) {
        LOGGER.debug("received request main page");

        return "main";
    }

}
