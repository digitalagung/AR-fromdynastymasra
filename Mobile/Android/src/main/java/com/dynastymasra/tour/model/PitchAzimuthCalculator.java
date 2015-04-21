package com.dynastymasra.tour.model;

import com.dynastymasra.tour.untility.Utilities;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class PitchAzimuthCalculator {
    private static final Vector LOOKING = new Vector();
    private static final float[] LOOKING_ARRAY = new float[3];

    private static volatile float azimuth = 0;

    private static volatile float pitch = 0;

    private PitchAzimuthCalculator() {}

    public static synchronized float getAzimuth() {
        return PitchAzimuthCalculator.azimuth;
    }
    public static synchronized float getPitch() {
        return PitchAzimuthCalculator.pitch;
    }

    public static synchronized void calcPitchBearing(Matrix rotationM) {
        if (rotationM==null) return;

        LOOKING.set(0, 0, 0);
        rotationM.transpose();
        LOOKING.set(1, 0, 0);
        LOOKING.prod(rotationM);
        LOOKING.get(LOOKING_ARRAY);
        PitchAzimuthCalculator.azimuth = ((Utilities.getAngle(0, 0, LOOKING_ARRAY[0], LOOKING_ARRAY[2])  + 360 ) % 360);

        rotationM.transpose();
        LOOKING.set(0, 1, 0);
        LOOKING.prod(rotationM);
        LOOKING.get(LOOKING_ARRAY);
        PitchAzimuthCalculator.pitch = -Utilities.getAngle(0, 0, LOOKING_ARRAY[1], LOOKING_ARRAY[2]);
    }
}