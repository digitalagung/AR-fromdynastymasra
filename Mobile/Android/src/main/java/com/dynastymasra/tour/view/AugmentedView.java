package com.dynastymasra.tour.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.dynastymasra.tour.activity.AugmentedActivity;
import com.dynastymasra.tour.model.ARData;
import com.dynastymasra.tour.model.Marker;
import com.dynastymasra.tour.model.Radar;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class AugmentedView extends View {
    private static final AtomicBoolean DRAWING = new AtomicBoolean(false);

    private static final Radar RADAR = new Radar();
    private static final float[] LOCATION_ARRAY = new float[3];
    private static final List<Marker> CACHE = new ArrayList<Marker>();
    private static final TreeSet<Marker> UPDATED = new TreeSet<Marker>();
    private static final int COLLISION_ADJUSTMENT = 100;

    public AugmentedView(Context context) {
        super(context);
    }

	@Override
    protected void onDraw(Canvas canvas) {
    	if (canvas==null) return;

        if (DRAWING.compareAndSet(false, true)) {
	        List<Marker> collection = ARData.getMarkers();

            CACHE.clear();
            for (Marker m : collection) {
                m.update(canvas, 0, 0);
                if (m.isOnRadar()) CACHE.add(m);
	        }
            collection = CACHE;

	        if (AugmentedActivity.useCollisionDetection) adjustForCollisions(canvas,collection);

	        ListIterator<Marker> iter = collection.listIterator(collection.size());
	        while (iter.hasPrevious()) {
	            Marker marker = iter.previous();
	            marker.draw(canvas);
	        }
	        if (AugmentedActivity.showRadar) RADAR.draw(canvas);
	        DRAWING.set(false);
        }
    }

	private static void adjustForCollisions(Canvas canvas, List<Marker> collection) {
	    UPDATED.clear();
        for (Marker marker1 : collection) {
            if (UPDATED.contains(marker1) || !marker1.isInView()) continue;

            int collisions = 1;
            for (Marker marker2 : collection) {
                if (marker1.equals(marker2) || UPDATED.contains(marker2) || !marker2.isInView()) continue;

                if (marker1.isMarkerOnMarker(marker2)) {
                    marker2.getLocation().get(LOCATION_ARRAY);
                    float y = LOCATION_ARRAY[1];
                    float h = collisions*COLLISION_ADJUSTMENT;
                    LOCATION_ARRAY[1] = y+h;
                    marker2.getLocation().set(LOCATION_ARRAY);
                    marker2.update(canvas, 0, 0);
                    collisions++;
                    UPDATED.add(marker2);
                }
            }
            UPDATED.add(marker1);
        }
	}
}