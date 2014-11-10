package com.squarecode.yogyatour.controller.api;

import com.squarecode.yogyatour.domain.Country;
import com.squarecode.yogyatour.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
@RestController
@RequestMapping("/api")
public class LocationApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationApiController.class);

    @Autowired
    private CountryRepository countryRepository;

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public List<Country> getAll() {
        List<Country> findAll = new ArrayList<Country>();
        for (int i=0; i<10; i++) {
            Country country = new Country();
            country.setCode(12);
            country.setCodes("ID");
            country.setId((long) 1);
            country.setName("tes");
            findAll.add(country);
        }
        return findAll;
    }
}
