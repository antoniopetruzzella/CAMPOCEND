package com.android.ggallery.museodellemarionette;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class PalazzoBalbi extends AppCompatActivity {
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palazzo_balbi);
        Global global=(Global)getApplicationContext();
        global.setInStaticActivity(true);
        String indirizzo=getIntent().getStringExtra("indirizzo");
        wv = (WebView) findViewById(R.id.webView);
        wv.loadUrl("file:///android_asset/"+indirizzo);

    }
}
