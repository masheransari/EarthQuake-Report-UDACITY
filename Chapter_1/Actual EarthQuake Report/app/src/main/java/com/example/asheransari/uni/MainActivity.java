package com.example.asheransari.uni;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.LoaderManager;
import android.content.Loader;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Customclass>>{

    private CustomAdapter mAdapter;
    private static final String USGS_REQUEST_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query";

//    private static final String USGS_REQUEST_URL =
//            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=20";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity", "Text: EarthQuake Activity onCreate() Called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyTextView = (TextView)findViewById(R.id.empty_view);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setEmptyView(mEmptyTextView);
        mAdapter = new CustomAdapter(this, new ArrayList<Customclass>());

        listView.setAdapter(mAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Customclass customclass = mAdapter.getItem(position);

                Uri earthQuakeUri = Uri.parse(customclass.getmUrl());

                Intent i = new Intent(Intent.ACTION_VIEW,earthQuakeUri);

                startActivity(i);

            }
        });

//        LoaderManager loaderManager = getLoaderManager();
//
//        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
        {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        }
        else
        {
            View LoadingIndicator = findViewById(R.id.loading_indicator);
            LoadingIndicator.setVisibility(View.GONE);

            mEmptyTextView.setText(R.string.NO_INTERNET_CONNECTION);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_setting)
        {
            Intent settingIntent = new Intent(this, setting_activity.class);
            startActivity(settingIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<List<Customclass>> onCreateLoader(int id, Bundle bundle) {
        Log.i("MainActivity","onCreateLoader() cAlled....");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = preferences.getString(getString(R.string.settings_min_magnitude_key), getString(R.string.settings_min_magnitude_default));

        String orderBy = preferences.getString(getString(R.string.settings_order_by_key), getString(R.string.settings_order_by_default));

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uBuilder = baseUri.buildUpon();

        uBuilder.appendQueryParameter("format","geojson");
        uBuilder.appendQueryParameter("limit","20");
        uBuilder.appendQueryParameter("minmag",minMagnitude);
        uBuilder.appendQueryParameter("orderby",orderBy);

        return new EarthQuakeLoader(this,uBuilder.toString());

//        return new EarthQuakeLoader(MainActivity.this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Customclass>> loader, List<Customclass> data) {

        Log.i("MainActivity", "OnLoadFinished() Called");

        View loadingIndictor = findViewById(R.id.loading_indicator);
        loadingIndictor.setVisibility(View.GONE);

        mEmptyTextView.setText(R.string.no_earthquakes);
        mAdapter.clear();
        if (data !=null && !data.isEmpty())
        {
            mAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Customclass>> loader) {
        Log.i("MainActivity", "OnLoadReset() Called...");
        mAdapter.clear();
    }
}
