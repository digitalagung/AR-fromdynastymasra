package com.dynastymasra.tour.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import android.location.Location;
import android.util.Log;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public abstract class ARData {
    private static final String TAG = "ARData";
	private static final Map<String,Marker> MARKER_LIST = new ConcurrentHashMap<>();
    private static final List<Marker> CACHE = new CopyOnWriteArrayList<>();
    private static final AtomicBoolean DIRTY = new AtomicBoolean(false);
    private static final float[] LOCATION_ARRAY = new float[3];
    public static final Location HARD_FIX = new Location("ATL");

    static {
        HARD_FIX.setLatitude(0);
        HARD_FIX.setLongitude(0);
        HARD_FIX.setAltitude(1);
    }
    
    private static final Object RADIUS_LOCK = new Object();
    private static float radius = new Float(20);
    private static String zoomLevel = new String();
    private static final Object ZOOM_PROGRESS_LOCK = new Object();
    private static int zoomProgress = 0;
    private static Location currentLocation = HARD_FIX;
    private static Matrix rotationMatrix = new Matrix();
    private static final Object AZIMUTH_LOCK = new Object();
    private static float azimuth = 0;
    private static final Object PITH_LOCK = new Object();
    private static float pitch = 0;
    private static final Object rollLock = new Object();
    private static float roll = 0;

    public static void setZoomLevel(String zoomLevel) {
    	if (zoomLevel==null) throw new NullPointerException();
    	synchronized (ARData.zoomLevel) {
    	    ARData.zoomLevel = zoomLevel;
    	}
    }
    
    public static void setZoomProgress(int zoomProgress) {
        synchronized (ARData.ZOOM_PROGRESS_LOCK) {
            if (ARData.zoomProgress != zoomProgress) {
                ARData.zoomProgress = zoomProgress;
                if (DIRTY.compareAndSet(false, true)) {
                    Log.v(TAG, "Setting DIRTY flag!");
                    CACHE.clear();
                }
            }
        }
    }
    
    public static void setRadius(float radius) {
        synchronized (ARData.RADIUS_LOCK) {
            ARData.radius = radius;
        }
    }

    public static float getRadius() {
        synchronized (ARData.RADIUS_LOCK) {
            return ARData.radius;
        }
    }

    public static void setCurrentLocation(Location currentLocation) {
    	if (currentLocation==null) throw new NullPointerException();
    	
    	Log.d(TAG, "current location. location="+currentLocation.toString());
    	synchronized (currentLocation) {
    	    ARData.currentLocation = currentLocation;
    	}
        onLocationChanged(currentLocation);
    }
    
    public static Location getCurrentLocation() {
        synchronized (ARData.currentLocation) {
            return ARData.currentLocation;
        }
    }

    public static void setRotationMatrix(Matrix rotationMatrix) {
        synchronized (ARData.rotationMatrix) {
            ARData.rotationMatrix = rotationMatrix;
        }
    }

    public static Matrix getRotationMatrix() {
        synchronized (ARData.rotationMatrix) {
            return rotationMatrix;
        }
    }
    
    public static List<Marker> getMarkers() {
        if (DIRTY.compareAndSet(true, false)) {
            Log.v(TAG, "DIRTY flag found, resetting all marker heights to zero.");
            for(Marker ma : MARKER_LIST.values()) {
                ma.getLocation().get(LOCATION_ARRAY);
                LOCATION_ARRAY[1]=ma.getInitialY();
                ma.getLocation().set(LOCATION_ARRAY);
            }

            Log.v(TAG, "Populating the cache.");
            List<Marker> copy = new ArrayList<>();
            copy.addAll(MARKER_LIST.values());
            Collections.sort(copy,comparator);
            CACHE.clear();
            CACHE.addAll(copy);
        }
        return Collections.unmodifiableList(CACHE);
    }

    public static void setAzimuth(float azimuth) {
        synchronized (AZIMUTH_LOCK) {
            ARData.azimuth = azimuth;
        }
    }

    public static float getAzimuth() {
        synchronized (AZIMUTH_LOCK) {
            return ARData.azimuth;
        }
    }

    public static void setPitch(float pitch) {
        synchronized (PITH_LOCK) {
            ARData.pitch = pitch;
        }
    }

    public static float getPitch() {
        synchronized (PITH_LOCK) {
            return ARData.pitch;
        }
    }

    public static void setRoll(float roll) {
        synchronized (rollLock) {
            ARData.roll = roll;
        }
    }

    public static float getRoll() {
        synchronized (rollLock) {
            return ARData.roll;
        }
    }
    
    private static final Comparator<Marker> comparator = new Comparator<Marker>() {
        public int compare(Marker arg0, Marker arg1) {
            return Double.compare(arg0.getDistance(),arg1.getDistance());
        }
    };

    public static void addMarkers(Collection<Marker> markers) {
    	if (markers==null) throw new NullPointerException();

    	if (markers.size()<=0) return;
    	
    	Log.d(TAG, "New markers, updating markers. new markers="+markers.toString());
    	for(Marker marker : markers) {
    	    if (!MARKER_LIST.containsKey(marker.getName())) {
    	        marker.calcRelativePosition(ARData.getCurrentLocation());
    	        MARKER_LIST.put(marker.getName(),marker);
    	    }
    	}

    	if (DIRTY.compareAndSet(false, true)) {
    	    Log.v(TAG, "Setting DIRTY flag!");
    	    CACHE.clear();
    	}
    }
    
    private static void onLocationChanged(Location location) {
        Log.d(TAG, "New location, updating markers. location="+location.toString());
        for(Marker ma: MARKER_LIST.values()) {
            ma.calcRelativePosition(location);
        }

        if (DIRTY.compareAndSet(false, true)) {
            Log.v(TAG, "Setting DIRTY flag!");
            CACHE.clear();
        }
    }
}