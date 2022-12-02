package fr.argouges.persomemo;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;

import fr.argouges.persomemo.ui.main.NIOFileSelectStore;
import fr.argouges.persomemo.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    public static String PATH_NAME;
    public static String PATH_ROOT;
    public static boolean PATH_DIR;
    public static boolean PATH_SDCARD = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage();
        PACKAGE_NAME = getApplicationContext().getPackageName();
        PATH_ROOT = this.getFilesDir().toString();
        PATH_NAME = getPref();
        PATH_DIR = getDir();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        //FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton ref = findViewById(R.id.ref);
        FloatingActionButton app = findViewById(R.id.app);
        ImageView pref = findViewById(R.id.pref);
        final Intent intent = new Intent().setClass(this, PreferenceActivity.class);
        final Intent intentcontact = new Intent().setClass(this, ContactActivity.class);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            askForPermissionStorage();
        }


        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "La note a été enregistrée !", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                //------------------------------------------------------------------------
            }
        });*/

        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                refresh(true);
                Boolean Statut = false;
                try {
                    Statut = NIOFileSelectStore.select(getPref());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!Statut) {
                    Snackbar.make(view, "Impossible d'enregistrer à l'endroit sélectionné", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(intent);
            }
        });

        app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(intentcontact);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh(true);
    }

    private void askForPermissionStorage()
    {
            requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 2);
    }

    public void refresh(boolean retour) {
        storage();
        PATH_NAME = getPref();
        PATH_DIR = getDir();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        //FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton ref = findViewById(R.id.ref);
    }

    public void storage() {
        String FAVORITE_STORAGE = "LinkStore";
        String GET_PREF = "LinkStoreDefault";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean FolderRoot = preferences.getBoolean("LinkStoreLocal",true);
        boolean PREFERRED = preferences.getBoolean("LinkStoreDefault",false);
        if(!PREFERRED && FolderRoot) {
            clearSharedPref();
        }
        String PREFERRED_STORAGE = preferences.getString("LinkStore",null);
        if(PREFERRED_STORAGE==null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(FAVORITE_STORAGE, "/Documents/");
            editor.apply();
        }
    }

    public String getPref() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String FolderSave = preferences.getString("LinkStore",null);
        return FolderSave;
    }

    public Boolean getDir() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean FolderRoot = preferences.getBoolean("LinkStoreLocal",true);
        return FolderRoot;
    }

    public void clearSharedPref() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}