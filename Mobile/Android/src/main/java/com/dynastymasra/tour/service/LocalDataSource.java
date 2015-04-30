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
    private static Bitmap iconAirport = null;
    private static Bitmap iconBus = null;
    private static Bitmap iconTrain = null;
    private static Bitmap iconBeach = null;
    private static Bitmap iconTemple = null;
    private static Bitmap iconPalace = null;
    private static Bitmap iconMuseum = null;
    private static Bitmap iconNature = null;
    private static Bitmap iconGasStation = null;
    private static Bitmap iconMosque = null;
    private static Bitmap iconATM = null;
    private static Bitmap iconKaaba = null;
    private Context context;
    
    public LocalDataSource(Resources res, Context context) {
        if (res == null) throw new NullPointerException();
        this.context = context;
        createIcon(res);
    }
    
    protected void createIcon(Resources res) {
        if (res == null) throw new NullPointerException();
        
        iconHotel = BitmapFactory.decodeResource(res, R.drawable.ic_hotel);
        iconAirport = BitmapFactory.decodeResource(res, R.drawable.ic_airport);
        iconBus = BitmapFactory.decodeResource(res, R.drawable.ic_bus);
        iconTrain = BitmapFactory.decodeResource(res, R.drawable.ic_train);
        iconBeach = BitmapFactory.decodeResource(res, R.drawable.ic_beach_maker);
        iconTemple = BitmapFactory.decodeResource(res, R.drawable.ic_temple);
        iconPalace = BitmapFactory.decodeResource(res, R.drawable.ic_palace_maker);
        iconMuseum = BitmapFactory.decodeResource(res, R.drawable.ic_museum_maker);
        iconNature = BitmapFactory.decodeResource(res, R.drawable.ic_nature_marker);
        iconGasStation = BitmapFactory.decodeResource(res, R.drawable.ic_gas_marker);
        iconMosque = BitmapFactory.decodeResource(res, R.drawable.ic_mosque_marker);
        iconATM = BitmapFactory.decodeResource(res, R.drawable.ic_atm_marker);
        iconKaaba = BitmapFactory.decodeResource(res, R.drawable.ic_kaaba_marker);
    }
    
    public List<Marker> getMarkers() {
        List<Content> contentList = ((MainApplication) context.getApplicationContext()).getDataApp().getContents();
        Log.i(TAG, "Size AR=>" + contentList.size());

        for (Content value : contentList) {
            Log.i(TAG, "value=>" + value.getIdLocation() + " " + value.getTitle() + " " + value.getAddress());
            if (value.getCategory().equals(Category.Temple)) {
                Log.i(TAG, "Temple=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconTemple);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Airport)) {
                Log.i(TAG, "Airport=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconAirport);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Terminal)) {
                Log.i(TAG, "Terminal=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconBus);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Station)) {
                Log.i(TAG, "Station=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconTrain);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Hotel)) {
                Log.i(TAG, "Hotel=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconHotel);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Beach)) {
                Log.i(TAG, "Beach=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconBeach);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Palace)) {
                Log.i(TAG, "Palace=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconPalace);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Museum)) {
                Log.i(TAG, "Museum=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconMuseum);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Nature)) {
                Log.i(TAG, "Nature=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconNature);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Gas)) {
                Log.i(TAG, "Gas Station=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconGasStation);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Mosque)) {
                Log.i(TAG, "Mosque=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconMosque);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.ATM)) {
                Log.i(TAG, "ATM=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconATM);
                cachedMarkers.add(marker);
            } else if (value.getCategory().equals(Category.Qiblat)) {
                Log.i(TAG, "Qiblat=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                Marker marker = new IconMarker(value.getTitle(), value.getLatitude(), value.getLongtitude(), 0,
                        Color.TRANSPARENT, iconKaaba);
                cachedMarkers.add(marker);
            }
        }

        return cachedMarkers;
    }
}