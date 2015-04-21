package com.dynastymasra.tour.service;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import com.dynastymasra.tour.R;
import com.dynastymasra.tour.model.IconMarker;
import com.dynastymasra.tour.model.Marker;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class LocalDataSource extends DataSource{
    private List<Marker> cachedMarkers = new ArrayList<Marker>();
    private static Bitmap icon = null;
    
    public LocalDataSource(Resources res) {
        if (res==null) throw new NullPointerException();
        
        createIcon(res);
    }
    
    protected void createIcon(Resources res) {
        if (res==null) throw new NullPointerException();
        
        icon=BitmapFactory.decodeResource(res, R.drawable.ic_hotel);
    }
    
    public List<Marker> getMarkers() {
        Marker atl = new IconMarker("Royal Ambarrukmo", -7.781868, 110.402147, 0, Color.TRANSPARENT, icon);
        cachedMarkers.add(atl);

        Marker home = new IconMarker("Saphir", -7.783521, 110.391349, 0, Color.TRANSPARENT, icon);
        cachedMarkers.add(home);

        Marker hyatt = new IconMarker("Hyatt Regency Yogyakarta", -7.739325, 110.373405, 0, Color.TRANSPARENT, icon);
        cachedMarkers.add(hyatt);

        return cachedMarkers;
    }
}