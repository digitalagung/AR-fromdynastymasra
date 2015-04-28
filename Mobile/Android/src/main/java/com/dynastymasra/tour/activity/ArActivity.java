package com.dynastymasra.tour.activity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.dynastymasra.tour.R;
import com.dynastymasra.tour.model.ARData;
import com.dynastymasra.tour.model.Marker;
import com.dynastymasra.tour.service.LocalDataSource;
import com.dynastymasra.tour.service.NetworkDataSource;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class ArActivity extends AugmentedActivity {
    private static final String TAG = "ArActivity";
    private static final String LOCALE = "en";
    private static final BlockingQueue<Runnable> QUEUE = new ArrayBlockingQueue<>(1);
    private static final ThreadPoolExecutor EXE_SERVICE = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, QUEUE);
    private static final Map<String, NetworkDataSource> SOURCES = new ConcurrentHashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalDataSource localData = new LocalDataSource(this.getResources(), this);
        ARData.addMarkers(localData.getMarkers());
    }

    @Override
    public void onStart() {
        super.onStart();
        Location last = ARData.getCurrentLocation();
        updateData(last.getLatitude(), last.getLongitude(), last.getAltitude());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v(TAG, "onOptionsItemSelected() item=" + item);
        switch (item.getItemId()) {
            case R.id.showRadar:
                showRadar = !showRadar;
                item.setTitle(((showRadar) ? "Hide" : "Show") + " Radar");
                break;
            case R.id.showZoomBar:
                showZoomBar = !showZoomBar;
                item.setTitle(((showZoomBar) ? "Hide" : "Show") + " Zoom Bar");
                zoomLayout.setVisibility((showZoomBar) ? LinearLayout.VISIBLE : LinearLayout.GONE);
                break;
            case R.id.exit:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        updateData(location.getLatitude(), location.getLongitude(), location.getAltitude());
    }

    @Override
    protected void markerTouched(Marker marker) {
        Toast t = Toast.makeText(getApplicationContext(), marker.getName(), Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }

    @Override
    protected void updateDataOnZoom() {
        super.updateDataOnZoom();
        Location last = ARData.getCurrentLocation();
        updateData(last.getLatitude(), last.getLongitude(), last.getAltitude());
    }

    private void updateData(final double lat, final double lon, final double alt) {
        try {
            EXE_SERVICE.execute(
                    new Runnable() {
                        public void run() {
                            for (NetworkDataSource source : SOURCES.values())
                                download(source, lat, lon, alt);
                        }
                    }
            );
        } catch (RejectedExecutionException rej) {
            Log.w(TAG, "Not running new download Runnable, queue is full.");
        } catch (Exception e) {
            Log.e(TAG, "Exception running download Runnable.", e);
        }
    }

    private static boolean download(NetworkDataSource source, double lat, double lon, double alt) {
        if (source == null) return false;

        String url;
        try {
            url = source.createRequestURL(lat, lon, alt, ARData.getRadius(), LOCALE);
        } catch (NullPointerException e) {
            return false;
        }
        List<Marker> markers;
        try {
            markers = source.parse(url);
        } catch (NullPointerException e) {
            return false;
        }

        ARData.addMarkers(markers);
        return true;
    }
}