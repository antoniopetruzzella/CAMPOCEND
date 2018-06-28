package com.android.ggallery.museodellemarionette;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener  {


    private Handler scanHandler = new Handler();
    private Handler startHandler=new Handler();
    private int scan_interval_ms = 750;
    private int start_interval_ms=5000;
    private int actualRoom=-9999;
    private boolean copertina=false;
    private ImageButton credits;
    private Intent primaStanzaIntent,secondaStanzaIntent,terzaStanzaIntent,quartaStanzaIntent,palazzobalbiIntent;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private static final int PERMISSION_REQUEST_FINE_LOCATION = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton credits=(ImageButton)findViewById(R.id.credits);
        credits.setOnClickListener(this);
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
        Global global=(Global)getApplicationContext();
        global.setInStaticActivity(false);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("COARSE LOC", "coarse location permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }
                    });
                    builder.show();
                }
                return;
            }
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        startHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                copertina = true;
            }
        },10000);
        startService(new Intent(this,GgalleryBeaconListenerIntentService.class));
        scanHandler.post(scanRunnable);
        primaStanzaIntent=new Intent(this, PrimaStanza.class);
        secondaStanzaIntent=new Intent(this, SecondaStanza.class);
        terzaStanzaIntent=new Intent(this, terza_stanza.class);
        quartaStanzaIntent=new Intent(this, QuartaStanza.class);
        palazzobalbiIntent=new Intent(this, PalazzoBalbi.class);


    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {

        }
    }



    private Runnable scanRunnable = new Runnable()
    {
        @Override
        public void run() {
            Global global=(Global)getApplicationContext();
           if(global.getNearestBeacon()!=null) {
               Log.i("ACTUAL UUID NEAREST", global.getNearestBeacon().getUuid());
               Log.i("ACTUAL MINOR NEAREST", global.getNearestBeacon().getMinor().toString());
               Log.i("ACTUAL MAJOR NEAREST", global.getNearestBeacon().getMajor().toString());
               if(copertina && global.getInStaticActivity()==false){
                   if(global.getCurrentMediaPlaying()==null) {
                       openRoomActivity(global.getNearestBeacon().getMajor());
                   }else{
                       if(global.getCurrentMediaPlaying().isPlaying()==false) {
                           openRoomActivity(global.getNearestBeacon().getMajor());
                       }
                   }
               }
           }
            scanHandler.postDelayed(this, scan_interval_ms);
        }
    };

    private void openRoomActivity(Integer major){

        if(major!=actualRoom){
            actualRoom=major;
            switch (major) {
                case 1:
                    startActivity(primaStanzaIntent);
                    break;
                case 2:
                    startActivity(secondaStanzaIntent);
                    break;
                case 3:
                    startActivity(terzaStanzaIntent);
                    break;
                case 4:
                    startActivity(quartaStanzaIntent);
                    break;
                default:
                    break;
            }
        }
    }
    public void onPostCreate(Bundle savedInstanceState,
                                PersistableBundle persistentState){


    }

    @Override
    public  void onClick(View view) {

        switch (view.getId()) {

            case R.id.credits:
                palazzobalbiIntent.putExtra("indirizzo","credits.html");
                startActivity(palazzobalbiIntent);
                break;

        }
    }
}
