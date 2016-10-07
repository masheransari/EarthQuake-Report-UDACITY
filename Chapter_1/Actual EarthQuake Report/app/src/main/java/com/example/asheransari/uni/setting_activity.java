package com.example.asheransari.uni;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class setting_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_activity);
    }

    public static class EarthquakePreferenceFragment extends PreferenceFragment
            implements Preference.OnPreferenceChangeListener{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_main);

            Preference minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key));
            bindpreferenceSummaryToValue(minMagnitude);

            Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
            bindpreferenceSummaryToValue(orderBy);
        }
        private void bindpreferenceSummaryToValue(Preference preferenceMethod)
        {
            preferenceMethod.setOnPreferenceChangeListener(this);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preferenceMethod.getContext());

            String preferenceString = preferences.getString(preferenceMethod.getKey(),"");

            onPreferenceChange(preferenceMethod, preferenceString);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value)
        {
            String strValue = value.toString();
            if (preference instanceof ListPreference)
            {
                ListPreference listPreference = (ListPreference)preference;

                int prefIndex = listPreference.findIndexOfValue(strValue);

                if (prefIndex >=0)
                {
                    CharSequence[] labels = listPreference.getEntries();

                    preference.setSummary(labels[prefIndex]);
                }
            }
            else
            {
                preference.setSummary(strValue);
            }
//            preference.setSummary(strValue);
            return true;
        }

    }

}
