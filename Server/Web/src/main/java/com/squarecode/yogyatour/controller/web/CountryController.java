package com.squarecode.yogyatour.controller.web;

import com.squarecode.yogyatour.domain.Country;
import com.squarecode.yogyatour.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
@Controller
public class CountryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryRepository countryRepository;

    @RequestMapping(value = "/admin/location", method = RequestMethod.GET)
    public String getLocationPage() {
        LOGGER.info("request show location page");
        return "fragment/location";
    }

    @RequestMapping(value = "/admin/location", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(Country country) {
        countryRepository.save(country);
        return "fragment/location";
    }

}
