package com.example.asheransari.uni;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by asher.ansari on 9/23/2016.
 */
public class CustomAdapter extends ArrayAdapter<Customclass>  {
    public static final String LOCATION_SEPERATOR = " of ";
CustomAdapter(Activity activity,ArrayList<Customclass> arrayAdapter){
    super(activity,0,arrayAdapter);
}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listitemview = convertView;

        if(listitemview == null){
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Customclass currentlist = getItem(position);

        TextView magnitude = (TextView) listitemview.findViewById(R.id.magnitudeview);

        String mag = formatMagnitude(currentlist.getMagnitude());

        magnitude.setText(mag);


        GradientDrawable magnitudeDrawabale = (GradientDrawable)magnitude.getBackground();

        int magnitudeColor = getMagnitudeColor(currentlist.getMagnitude());

        magnitudeDrawabale.setColor(magnitudeColor);





        String PrimaryLocation, offsetLocation;
        TextView pri_Location = (TextView) listitemview.findViewById(R.id.primary_location);
        TextView Off_Location = (TextView) listitemview.findViewById(R.id.location_offset);


        String loca = currentlist.getPlace();

        if (loca.contains(LOCATION_SEPERATOR)) {
            String[] parts = loca.split(LOCATION_SEPERATOR);
            offsetLocation = parts[0] + LOCATION_SEPERATOR;
            PrimaryLocation =parts[1];
        }
        else
        {
            offsetLocation = getContext().getString(R.string.near_the);
            PrimaryLocation = loca;
        }

        pri_Location.setText(PrimaryLocation);
        Off_Location.setText(offsetLocation);


        TextView date = (TextView) listitemview.findViewById(R.id.date);

        Date dateobject = new Date(currentlist.getTime());

        String formatteddate = formateDate(dateobject);
        date.setText(formatteddate);

        TextView time = (TextView) listitemview.findViewById(R.id.time);

        String formattedtime = formatTime(dateobject);

        time.setText(formattedtime);

        return listitemview;
    }


    private String formateDate(Date dateObject)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject)
    {
        SimpleDateFormat TimeFormat = new SimpleDateFormat("h:mm a");
        String a = TimeFormat.format(dateObject);
        return a;
    }

    private String formatMagnitude(double magnitude)
    {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(magnitude);
    }


private int getMagnitudeColor(double magnitude)
{
    int magnitudeColorResourceID;
    int magnitudeFloor = (int)Math.floor(magnitude);

    switch(magnitudeFloor)
    {
        case 1:
            magnitudeColorResourceID = R.color.magnitude1;
            break;
        case 2:
            magnitudeColorResourceID = R.color.magnitude2;
            break;
        case 3:
            magnitudeColorResourceID = R.color.magnitude3;
            break;
        case 4:
            magnitudeColorResourceID = R.color.magnitude4;
            break;
        case 5:
            magnitudeColorResourceID = R.color.magnitude5;
            break;
        case 6:
            magnitudeColorResourceID = R.color.magnitude6;
            break;
        case 7:
            magnitudeColorResourceID = R.color.magnitude7;
            break;
        case 8:
            magnitudeColorResourceID = R.color.magnitude8;
            break;
        case 9:
            magnitudeColorResourceID = R.color.magnitude9;
            break;
        default:
            magnitudeColorResourceID = R.color.magnitude10plus;
            break;
    }
    return ContextCompat.getColor(getContext(), magnitudeColorResourceID);
}

}

