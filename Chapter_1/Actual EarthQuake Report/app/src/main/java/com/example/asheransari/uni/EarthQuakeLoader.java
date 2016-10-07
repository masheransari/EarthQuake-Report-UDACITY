package com.example.asheransari.uni;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by asher.ansari on 10/6/2016.
 */
public class EarthQuakeLoader extends AsyncTaskLoader<List<Customclass>>{

    private static final String LOG_TAG = EarthQuakeLoader.class.getName();
    private String mUrl;

    public EarthQuakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
        Log.i(LOG_TAG, "In EarthQuakeLoader called....");
    }

    @Override
    protected void onStartLoading()
    {
        Log.i(LOG_TAG, "onStartLoading called...");
        forceLoad();
    }

    @Override
    public List<Customclass> loadInBackground() {
    if (mUrl == null)
    {
        return null;
    }
        Log.i(LOG_TAG, "in custom list method called....");
    List<Customclass> customclasses = Queryutil.fetchEarthQuakeData(mUrl);
        return customclasses;
    }
}
