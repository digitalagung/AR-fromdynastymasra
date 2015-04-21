package com.dynastymasra.tour.service;

import com.dynastymasra.tour.model.Marker;

import java.util.List;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public abstract class DataSource {
    public abstract List<Marker> getMarkers();
}
