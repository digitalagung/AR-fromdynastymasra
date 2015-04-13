package com.dynastymasra.tour.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.dynastymasra.tour.MainApplication;
import com.dynastymasra.tour.R;
import com.dynastymasra.tour.adapter.MenuDrawerAdapter;
import com.dynastymasra.tour.domain.Content;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class HomeActivity extends Activity {
    private final static String TAG = "HomeActivity";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private MenuDrawerAdapter menuDrawerAdapter;
    private List<String> dataList;
    private GoogleMap map;
    private List<Content> contents;
    private LocationManager locationManager;
    private LocationListener locationListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataList = new ArrayList<>();
//        contents = ((MainApplication) getApplicationContext()).getDataApp().getContents();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        dataList.add("Hotel");
        dataList.add("Temple");
        dataList.add("Station");
        dataList.add("Terminal");
        dataList.add("Airport");

        menuDrawerAdapter = new MenuDrawerAdapter(this, dataList);
        mDrawerList.setAdapter(menuDrawerAdapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };

        getMaps();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

//        switch (item.getItemId()) {
//            case R.id.action_camera:
//                Intent intent = new Intent(MainActivity.this, ArActivity.class);
//                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
        return super.onOptionsItemSelected(item);
    }

    private GoogleMap.OnMyLocationChangeListener onMyLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {

        @Override
        public void onMyLocationChange(Location location) {
            Log.i(TAG, "onMyLocationChange(Latitude=" + location.getLatitude() + ", Longtitude=" + location.getLongitude() + ")");
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            map.setMyLocationEnabled(true);
            map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    };

    public void getMaps() {
        if(map == null) {
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            map.getUiSettings().setCompassEnabled(true);
            map.getUiSettings().setZoomControlsEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            map.getUiSettings().setAllGesturesEnabled(true);
            map.setMyLocationEnabled(true);
            map.setTrafficEnabled(true);
            map.setOnMyLocationChangeListener(onMyLocationChangeListener);
        }
    }
}
