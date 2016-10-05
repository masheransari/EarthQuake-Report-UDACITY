package com.example.asheransari.earthquake;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by asher.ansari on 9/23/2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> arrayList) {
        super(context, 0 ,arrayList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listdetails, parent, false);
        }

        Earthquake earthquake = getItem(position);
        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitude.setText(earthquake.getMagnitude());

        TextView place = (TextView) listItemView.findViewById(R.id.place);
        place.setText(earthquake.getPlace());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(earthquake.getDate());

        return listItemView;
    }
}
