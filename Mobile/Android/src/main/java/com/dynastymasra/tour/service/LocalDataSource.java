package com.dynastymasra.tour.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import com.dynastymasra.tour.MainApplication;
import com.dynastymasra.tour.R;
import com.dynastymasra.tour.domain.Content;
import com.dynastymasra.tour.domain.enums.Category;
import com.dynastymasra.tour.model.IconMarker;
import com.dynastymasra.tour.model.Marker;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class LocalDataSource extends DataSource {
    private static final String TAG = "DataSource";
    private List<Marker> cachedMarkers = new ArrayList<>();
    private static Bitmap iconHotel = null;
    private Context context;
    
    public LocalDataSource(Resources res, Context context) {
        if (res == null) throw new NullPointerException();
        this.context = context;
        createIcon(res);
    }
    
    protected void createIcon(Resources res) {
        if (res == null) throw new NullPointerException();
        
        iconHotel = BitmapFactory.decodeResource(res, R.drawable.ic_hotel);
    }
    
    public List<Marker> getMarkers() {
        List<Content> contentList = ((MainApplication) context.getApplicationContext()).getDataApp().getContents();
        Log.i(TAG, "Size AR=>" + contentList.size());

        for (Content content: contentList) {
//            if (content.getCategory().equals(Category.Hotel)) {
            Log.i(TAG, "title=>" + content.getTitle() + ", description=>" + content.getDescription());
                Marker marker = new IconMarker(content.getTitle(), content.getLatitude(), content.getLongtitude(), 0,
                        Color.TRANSPARENT, iconHotel);
                cachedMarkers.add(marker);
//            }
        }

        return cachedMarkers;
    }
}