package com.dynastymasra.tour.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.dynastymasra.tour.R;

import java.util.List;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public class MenuDrawerAdapter extends BaseAdapter {

    private Context context;
    private List<String> drawerList;

    public MenuDrawerAdapter(Context context, List<String> drawerList) {
        this.context = context;
        this.drawerList = drawerList;
    }

    @Override
    public int getCount() {
        return drawerList.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.menu_drawer, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.text_menu_drawer);
        textView.setText(drawerList.get(position));

        return convertView;
    }
}
