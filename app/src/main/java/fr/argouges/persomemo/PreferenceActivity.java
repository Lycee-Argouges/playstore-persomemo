package fr.argouges.persomemo;

import android.os.Bundle;

public class PreferenceActivity extends android.preference.PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_main);
    }

}
