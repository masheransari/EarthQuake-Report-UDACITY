package com.example.asheransari.uni;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Customclass> arrayList = Queryutil.extractEarthquakes();

        ListView listView = (ListView) findViewById(R.id.list);

        final CustomAdapter customAdapter = new CustomAdapter(this,arrayList);

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Customclass customclass = customAdapter.getItem(position);

                Uri earthQuakeUri = Uri.parse(customclass.getmUrl());

                Intent i = new Intent(Intent.ACTION_VIEW,earthQuakeUri);

                startActivity(i);

            }
        });
    }
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Customclass>> {

        @Override
        protected List<Customclass> doInBackground(String... urls)
        {

        }

        @Override
        protected void onPostExecute(List<Customclass> data) {

        }
    }

}
