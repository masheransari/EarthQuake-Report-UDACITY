package com.example.asheransari.uni;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by asher.ansari on 9/23/2016.
 */
public final class Queryutil{
        /**
         * Sample JSON response for a USGS query
         */
        public static final String LOG_TAG = Queryutil.class.getSimpleName();
//    public static final String SAMPLE_JSON_RESPONSE = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=20";

        private Queryutil() {
        }


    public static URL createUrl(String stringUrl)
    {
        Log.i(LOG_TAG, "in createURl called....");
        URL url = null;
        try {
            url = new URL(stringUrl);
        }
        catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
    return url;
    }

    private static String makeHttpRequest(URL url) throws IOException
    {
        Log.i(LOG_TAG, "in makeHttpRequest called.....");
        String jsonResponse = "";
        if (url == null)
        {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try
        {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200)
            {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else
            {
                Log.e(LOG_TAG, "Error Response Code: "+urlConnection.getResponseCode());
            }
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG, "Problem retrieving the eartjquake JSON reults.", e);
        }

        finally
        {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
            if (inputStream !=null)
            {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream)throws IOException
    {
        Log.i(LOG_TAG, "in readFromStream called...");
        StringBuilder output = new StringBuilder();
        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line !=null)
            {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Customclass> extractFeatureFromJson(String earthquakeJSON)
    {
        Log.i(LOG_TAG, "in extractFeatureFromJson called....");
        if (TextUtils.isEmpty(earthquakeJSON))
        {
            return null;
        }
        List<Customclass> earthQuakes = new ArrayList<>();

        try
        {
            JSONObject baseJsonResponce = new JSONObject(earthquakeJSON);
            JSONArray earthQuakeArray = baseJsonResponce.getJSONArray("features");
            for (int i=0; i<earthQuakeArray.length(); i++)
            {
                JSONObject currentEarthQuake = earthQuakeArray.getJSONObject(i);
                JSONObject properties = currentEarthQuake.getJSONObject("properties");
                double magnitude = properties.getDouble("mag");
                String location = properties.getString("place");
                long time = properties.getLong("time");
                String url = properties.getString("url");
                Customclass customclass = new Customclass(magnitude,location, time, url);
                earthQuakes.add(customclass);
            }
        }
        catch(JSONException e)
        {
            Log.e("QueryUtil", "problem parsing the EarthQuake JSON results ",e);
        }
        return earthQuakes;
    }

    public static List<Customclass> fetchEarthQuakeData(String requestURL)
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i(LOG_TAG, "in fetchEarthQuakeData called....");
        URL url = createUrl(requestURL);
        String jsonResponce = null;
        try
        {
            jsonResponce = makeHttpRequest(url);
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG,"Problem making the HTTP request.",e);
        }

        List<Customclass> customclasses = extractFeatureFromJson(jsonResponce);

        return customclasses;
    }

}
