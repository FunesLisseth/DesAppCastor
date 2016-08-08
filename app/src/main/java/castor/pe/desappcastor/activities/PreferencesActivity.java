package castor.pe.desappcastor.activities;

import android.preference.PreferenceActivity;
import android.os.Bundle;
import castor.pe.desappcastor.R;


public class PreferencesActivity extends PreferenceActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);



    }

}
