package com.dynastymasra.tour;

import android.app.Application;
import com.dynastymasra.tour.domain.Data;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class MainApplication extends Application {
    public Data dataApp;

    public Data getDataApp() {
        return dataApp;
    }

    public void setDataApp(Data dataApp) {
        this.dataApp = dataApp;
    }
}
