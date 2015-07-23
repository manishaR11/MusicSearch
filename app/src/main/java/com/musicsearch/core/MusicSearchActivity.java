package com.musicsearch.core;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

import com.musicsearch.R;


public class MusicSearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_search);
        // override the thread policy  hack for running network operations on main ui thread
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Launching search fragment
        getFragmentManager().beginTransaction()
                .add(R.id.container, new MusicSearchFrgament())
                .commit();
    }

}
