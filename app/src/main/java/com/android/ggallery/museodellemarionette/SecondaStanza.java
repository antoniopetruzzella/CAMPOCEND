package com.android.ggallery.museodellemarionette;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.Console;
import java.lang.reflect.Field;


public class SecondaStanza extends AppCompatActivity implements SurfaceHolder.Callback,  View.OnClickListener{

    private MediaPlayer provaVideoPlayer, sleepAudioPlayer;
    private SurfaceHolder holder;
    private SurfaceView surface;
    private Button videoplaybtn, videopausebtn, sleepplaybtn, sleeppausebtn,approfondimentibtn;
    private SeekBar timeline;
    private Intent palazzobalbiIntent,videoIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconda_stanza);
        surface = (SurfaceView) findViewById(R.id.surfaceview);
        videoplaybtn=(Button)findViewById(R.id.videoplaybtn);
        videopausebtn=(Button)findViewById(R.id.videopausebtn);
        sleepplaybtn=(Button)findViewById(R.id.sleepplaybtn);
        sleeppausebtn=(Button)findViewById(R.id.sleeppausebtn);
        approfondimentibtn=(Button)findViewById(R.id.approfondimentibtn);
        palazzobalbiIntent=new Intent(this, PalazzoBalbi.class);
        videoIntent=new Intent(this,Video.class);
        videoplaybtn.setOnClickListener(this);
        videopausebtn.setOnClickListener(this);
        timeline=(SeekBar)findViewById(R.id.timeline);
        sleepplaybtn.setOnClickListener(this);
        sleeppausebtn.setOnClickListener(this);
        approfondimentibtn.setOnClickListener(this);
        holder = surface.getHolder();
        holder.addCallback(this);
        sleepAudioPlayer=MediaPlayer.create(this,R.raw.sleep);
        handler.post(runnable);


    }

    @Override
    protected  void onStop(){
        super.onStop();
        handler.removeCallbacks(runnable);
        provaVideoPlayer.release();
        sleepAudioPlayer.release();

    }
    @Override
    protected void onPause(){



        super.onPause();

    }




    @Override
    protected void onResume(){

        super.onResume();

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        provaVideoPlayer=MediaPlayer.create(this, R.raw.prova);
        provaVideoPlayer.setDisplay(holder);

    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        provaVideoPlayer.release();

    }

    @Override
    public  void onClick(View view){

        switch (view.getId()){

            case R.id.videoplaybtn:
                timeline.setMax(provaVideoPlayer.getDuration());
                provaVideoPlayer.start();
                break;

            case R.id.videopausebtn:
                provaVideoPlayer.pause();
                break;

            case R.id.sleepplaybtn:
                timeline.setMax(sleepAudioPlayer.getDuration());
                sleepAudioPlayer.start();
                break;

            case R.id.sleeppausebtn:
                sleepAudioPlayer.pause();
                break;
            case R.id.approfondimentibtn:

                approfondimentiClicked(approfondimentibtn);
                break;

        }



    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {


        try {
            if (provaVideoPlayer != null) {
                if (provaVideoPlayer.isPlaying()) {
                    timeline.setProgress(provaVideoPlayer.getCurrentPosition());
                }
            }

            if (sleepAudioPlayer != null) {
                if (sleepAudioPlayer.isPlaying()) {
                    timeline.setProgress(sleepAudioPlayer.getCurrentPosition());

                }
            }
        }catch (Exception ex){}


            handler.postDelayed(this, 500);
        }

    };

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message mess) {

            }

        };

    private void approfondimentiClicked(View v){

        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main_menu, popup.getMenu());
        Object menuHelper;
        Class[] argTypes;
        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popup);
            argTypes = new Class[]{boolean.class};
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
        } catch (Exception e) {
            // Possible exceptions are NoSuchMethodError and NoSuchFieldError
            //
            // In either case, an exception indicates something is wrong with the reflection code, or the
            // structure of the PopupMenu class or its dependencies has changed.
            //
            // These exceptions should never happen since we're shipping the AppCompat library in our own apk,
            // but in the case that they do, we simply can't force icons to display, so log the error and
            // show the menu normally.
        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){

                    case R.id.palazzo_balbi:
                        palazzobalbiIntent.putExtra("indirizzo","palazzobalbi.html");
                        startActivity(palazzobalbiIntent);
                        break;
                    case R.id.video01:
                        videoIntent.putExtra("video","video01");
                        videoIntent.putExtra("titolo","Introduzione alla collezione");
                        startActivity(videoIntent);
                        break;

                }
                return true;
            }
        });
        popup.show();
    }
}
