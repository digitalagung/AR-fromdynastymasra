package com.squarecode.yogyatour.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
@RestController
@RequestMapping("/api")
public class UserApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserApi(ModelMap modelMap) {
        LOGGER.info("request user api");
        return "home";
    }
}
