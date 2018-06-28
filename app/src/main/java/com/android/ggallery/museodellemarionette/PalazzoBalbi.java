package com.android.ggallery.museodellemarionette;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

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
        Context context = getApplicationContext();
        CharSequence text = "Usa il tasto back del tablet per tornare alla stanza";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    protected void onResume(){

        Global global=(Global)getApplicationContext();
        global.setInStaticActivity(false);
        super.onResume();
    }
}
