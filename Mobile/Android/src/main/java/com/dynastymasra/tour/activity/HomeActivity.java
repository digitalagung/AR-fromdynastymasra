package com.dynastymasra.tour.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.dynastymasra.tour.MainApplication;
import com.dynastymasra.tour.R;
import com.dynastymasra.tour.adapter.MenuDrawerAdapter;
import com.dynastymasra.tour.domain.Content;
import com.dynastymasra.tour.domain.enums.Category;
import com.dynastymasra.tour.domain.url.Url;
import com.dynastymasra.tour.helper.AlertDialogHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<Marker, Content> valueContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataList = new ArrayList<>();
        if (((MainApplication) getApplicationContext()).getDataApp() == null) {
            AlertDialogHelper.informationAlert(this, "Error 404", "Data not found in server", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        } else {
            contents = ((MainApplication) getApplicationContext()).getDataApp().getContents();
        }

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        dataList.add("All");
        dataList.add("Hotel");
        dataList.add("Airport");
        dataList.add("Station");
        dataList.add("Terminal");
        dataList.add("Temple");
        dataList.add("Beach");
        dataList.add("Palace");
        dataList.add("Museum");
        dataList.add("Nature");
        dataList.add("Gas Station");
        dataList.add("Mosque");
        dataList.add("ATM");
        dataList.add("Qiblat");

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
    public void onBackPressed() {
        finish();
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

        switch (item.getItemId()) {
            case R.id.action_map:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_ar:
                Intent intent1 = new Intent(this, ArActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        valueContent = new HashMap<>();
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
                Log.i(TAG, "value=>" + value.getIdLocation() + " " + value.getTitle() + " " + value.getAddress());
                if (value.getCategory().equals(Category.Temple)) {
                    Log.i(TAG, "Temple=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                    Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                            .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_temple)));
                    valueContent.put(marker, value);
                } else if (value.getCategory().equals(Category.Airport)) {
                    Log.i(TAG, "Airport=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                    Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                            .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_airport)));
                    valueContent.put(marker, value);
                } else if (value.getCategory().equals(Category.Terminal)) {
                    Log.i(TAG, "Terminal=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                    Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                            .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_bus)));
                    valueContent.put(marker, value);
                } else if (value.getCategory().equals(Category.Station)) {
                    Log.i(TAG, "Station=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                    Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                            .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_train)));
                    valueContent.put(marker, value);
                } else if (value.getCategory().equals(Category.Hotel)) {
                    Log.i(TAG, "Hotel=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                    Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                            .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_hotel)));
                    valueContent.put(marker, value);
                } else if (value.getCategory().equals(Category.Beach)) {
                    Log.i(TAG, "Hotel=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                    Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                            .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_beach_maker)));
                    valueContent.put(marker, value);
                }
            }

            map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    final Dialog dialog = new Dialog(HomeActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_view);

                    ImageView imagePlace = (ImageView) dialog.findViewById(R.id.dialog_pic);
                    TextView title = (TextView) dialog.findViewById(R.id.text_title);
                    TextView address = (TextView) dialog.findViewById(R.id.text_address);
                    TextView description = (TextView) dialog.findViewById(R.id.text_content);

                    Content content = valueContent.get(marker);
                    Log.i(TAG, "URL=>" + Url.URL_PHOTO + content.getPhotoUrl());

                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                            .resetViewBeforeLoading(true).showImageForEmptyUri(R.drawable.main_logo)
                            .showImageOnFail(R.drawable.main_logo).showImageOnLoading(R.drawable.main_logo).build();
                    ImageLoader.getInstance().displayImage(Url.URL_PHOTO + content.getPhotoUrl(), imagePlace, options);
                    title.setText(content.getTitle());
                    address.setText(content.getAddress());
                    description.setText(content.getDescription());
                    Button dialogButton = (Button) dialog.findViewById(R.id.button_dismiss);

                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    public void selectItem(int position) {
        valueContent = new HashMap<>();
        switch (position) {
            case 0:
                map.clear();
                for (Content value : contents) {
                    Log.i(TAG, "value=>" + value.getIdLocation() + " " + value.getTitle() + " " + value.getAddress());
                    if (value.getCategory().equals(Category.Temple)) {
                        Log.i(TAG, "Temple=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_temple)));
                        valueContent.put(marker, value);
                    } else if (value.getCategory().equals(Category.Airport)) {
                        Log.i(TAG, "Airport=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_airport)));
                        valueContent.put(marker, value);
                    } else if (value.getCategory().equals(Category.Terminal)) {
                        Log.i(TAG, "Terminal=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_bus)));
                        valueContent.put(marker, value);
                    } else if (value.getCategory().equals(Category.Station)) {
                        Log.i(TAG, "Station=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_train)));
                        valueContent.put(marker, value);
                    } else if (value.getCategory().equals(Category.Hotel)) {
                        Log.i(TAG, "Hotel=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_hotel)));
                        valueContent.put(marker, value);
                    } else if (value.getCategory().equals(Category.Beach)) {
                        Log.i(TAG, "Hotel=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_beach_maker)));
                        valueContent.put(marker, value);
                    }
                }
                break;
            case 1:
                map.clear();
                for (Content value : contents) {
                    Log.i(TAG, "value=>" + value.getIdLocation() + " " + value.getTitle() + " " + value.getAddress());
                    if (value.getCategory().equals(Category.Hotel)) {
                        Log.i(TAG, "Hotel=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_hotel)));
                        valueContent.put(marker, value);
                    }
                }
                break;
            case 2:
                map.clear();
                for (Content value : contents) {
                    Log.i(TAG, "value=>" + value.getIdLocation() + " " + value.getTitle() + " " + value.getAddress());
                    if (value.getCategory().equals(Category.Airport)) {
                        Log.i(TAG, "Airport=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_airport)));
                        valueContent.put(marker, value);
                    }
                }
                break;
            case 3:
                map.clear();
                for (Content value : contents) {
                    Log.i(TAG, "value=>" + value.getIdLocation() + " " + value.getTitle() + " " + value.getAddress());
                    if (value.getCategory().equals(Category.Station)) {
                        Log.i(TAG, "Station=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_train)));
                        valueContent.put(marker, value);
                    }
                }
                break;
            case 4:
                map.clear();
                for (Content value : contents) {
                    Log.i(TAG, "value=>" + value.getIdLocation() + " " + value.getTitle() + " " + value.getAddress());
                    if (value.getCategory().equals(Category.Terminal)) {
                        Log.i(TAG, "Terminal=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_bus)));
                        valueContent.put(marker, value);
                    }
                }
                break;
            case 5:
                map.clear();
                for (Content value : contents) {
                    Log.i(TAG, "value=>" + value.getIdLocation() + " " + value.getTitle() + " " + value.getAddress());
                    if (value.getCategory().equals(Category.Temple)) {
                        Log.i(TAG, "Temple=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_temple)));
                        valueContent.put(marker, value);
                    }
                }
                break;
            case 6:
                map.clear();
                for (Content value : contents) {
                    Log.i(TAG, "value=>" + value.getIdLocation() + " " + value.getTitle() + " " + value.getAddress());
                    if (value.getCategory().equals(Category.Beach)) {
                        Log.i(TAG, "Beach=>" + value.getTitle() + " " + value.getLatitude() + " " + value.getLongtitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(value.getLatitude(), value.getLongtitude()))
                                .title(value.getTitle()).snippet(value.getDescription()).icon(BitmapDescriptorFactory
                                        .fromResource(R.drawable.ic_beach_maker)));
                        valueContent.put(marker, value);
                    }
                }
                break;
            default:
                map.clear();
                break;
        }

        mDrawerList.setItemChecked(position, true);
        setTitle(dataList.get(position));
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
