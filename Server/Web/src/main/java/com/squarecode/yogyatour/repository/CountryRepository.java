package com.squarecode.yogyatour.repository;

import com.squarecode.yogyatour.domain.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
@RepositoryRestResource(collectionResourceRel = "country", path = "country")
public interface CountryRepository extends PagingAndSortingRepository<Country, String> {
}
