package com.dynastymasra.tour.model;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class CameraModel {
    private static final float[] TMP_1 = new float[3];
    private static final float[] TMP_2 = new float[3];

	private int width = 0; 
	private int height = 0;
	private float distance = 0F;
	
	public static final float DEFAULT_VIEW_ANGLE = (float) Math.toRadians(45);

	public CameraModel(int width, int height, boolean init) {
		set(width, height, init);
	}

	public void set(int width, int height, boolean init) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
	    return width;
	}

    public int getHeight() {
        return height;
    }

	public void setViewAngle(float viewAngle) {
		this.distance = (this.width / 2) / (float) Math.tan(viewAngle / 2);
	}
	
	public void projectPoint(Vector orgPoint, Vector prjPoint, float addX, float addY) {
	    orgPoint.get(TMP_1);
	    TMP_2[0]=(distance * TMP_1[0] / -TMP_1[2]);
	    TMP_2[1]=(distance * TMP_1[1] / -TMP_1[2]);
	    TMP_2[2]=(TMP_1[2]);
	    TMP_2[0]=(TMP_2[0] + addX + width / 2);
	    TMP_2[1]=(-TMP_2[1] + addY + height / 2);
	    prjPoint.set(TMP_2);
	}
}