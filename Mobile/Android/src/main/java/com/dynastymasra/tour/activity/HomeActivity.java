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
import android.widget.AdapterView;
import android.widget.ListView;
import com.dynastymasra.tour.MainApplication;
import com.dynastymasra.tour.R;
import com.dynastymasra.tour.adapter.MenuDrawerAdapter;
import com.dynastymasra.tour.domain.Content;
import com.dynastymasra.tour.domain.enums.Category;
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
    private final static LatLng YOGYAKARTA = new LatLng(-7.797069, 110.37053);
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
        contents = ((MainApplication) getApplicationContext()).getDataApp().getContents();
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
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

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
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(YOGYAKARTA, 15));
            map.setOnMyLocationChangeListener(onMyLocationChangeListener);

            for (Content value : contents) {
                if (value.getCategory().equals(Category.Temple)) {
                    map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                            .title(value.getTitle()).snippet(value.getDescription()));
                } else if (value.getCategory().equals(Category.Airport)) {
                    map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                            .title(value.getTitle()).snippet(value.getDescription()));
                } else if (value.getCategory().equals(Category.Terminal)) {
                    map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                            .title(value.getTitle()).snippet(value.getDescription()));
                } else if (value.getCategory().equals(Category.Station)) {
                    map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                            .title(value.getTitle()).snippet(value.getDescription()));
                }
            }
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    public void selectItem(int position) {
        switch (position) {
            case 0:
                map.clear();
                break;
            case 1:
                map.clear();
                for (Content value : contents) {
                    if (value.getCategory().equals(Category.Temple)) {
                        map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()));
                    }
                }
                break;
        }

        mDrawerList.setItemChecked(position, true);
        setTitle(dataList.get(position));
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
