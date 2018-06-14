package com.android.ggallery.museodellemarionette;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.lang.reflect.Field;
import java.security.PrivilegedAction;

public class PrimaStanza extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {

private MediaPlayer antoVideoPlayer, kalimbaAudioPlayer, maidAudioPlayer;
private SurfaceHolder holder;
private SurfaceView surface;
private Button  videopausebtn, kalimbaplaybtn, kalimbapausebtn, maidplaybtn, maidpausebtn,approfondimentibtn;
private ImageButton videoplaybtn;
private SeekBar timeline;
private Intent palazzobalbiIntent,videoIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prima_stanza);
        surface = (SurfaceView) findViewById(R.id.surfaceview);
        videoplaybtn=(ImageButton)findViewById(R.id.videoplaybtn);
        videopausebtn=(Button)findViewById(R.id.videopausebtn);
        kalimbaplaybtn=(Button)findViewById(R.id.kalimbaplaybtn);
        kalimbapausebtn=(Button)findViewById(R.id.kalimbapausebtn);
        maidplaybtn=(Button)findViewById(R.id.maidplaybtn);
        maidpausebtn=(Button)findViewById(R.id.maidpausebtn);
        approfondimentibtn=(Button)findViewById(R.id.approfondimentibtn);
        timeline=(SeekBar)findViewById(R.id.timeline);
        //palazzo_balbi=(MenuItem)findViewById(R.id.palazzo_balbi);
        videoplaybtn.setOnClickListener(this);
        videopausebtn.setOnClickListener(this);
        kalimbaplaybtn.setOnClickListener(this);
        kalimbapausebtn.setOnClickListener(this);
        maidplaybtn.setOnClickListener(this);
        maidpausebtn.setOnClickListener(this);
        palazzobalbiIntent=new Intent(this, PalazzoBalbi.class);
        videoIntent=new Intent(this,Video.class);
        approfondimentibtn.setOnClickListener(this);
        holder = surface.getHolder();
        holder.addCallback(this);
        kalimbaAudioPlayer=MediaPlayer.create(this,R.raw.kalimba);
        maidAudioPlayer=MediaPlayer.create(this,R.raw.maid);

        handler.post(runnable);
    }

    @Override
    protected void onPause(){


        super.onPause();

    }

    @Override
    protected void onStop(){

        super.onStop();
        handler.removeCallbacks(runnable);
        kalimbaAudioPlayer.release();
        maidAudioPlayer.release();
        antoVideoPlayer.release();
    }

    @Override
    protected void onResume(){

        super.onResume();

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        antoVideoPlayer=MediaPlayer.create(this, R.raw.anto);
        antoVideoPlayer.setDisplay(holder);

    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public  void onClick(View view){

        switch (view.getId()){

            case R.id.videoplaybtn:
                timeline.setMax(antoVideoPlayer.getDuration());
                antoVideoPlayer.start();
                //kalimbaAudioPlayer.pause();
                //maidAudioPlayer.pause();
                break;

            case R.id.videopausebtn:
                antoVideoPlayer.pause();
                break;

            case R.id.kalimbaplaybtn:
                timeline.setMax(kalimbaAudioPlayer.getDuration());
                kalimbaAudioPlayer.start();
                //antoVideoPlayer.pause();
                //maidAudioPlayer.pause();
                break;

            case R.id.kalimbapausebtn:
                kalimbaAudioPlayer.pause();
                break;

            case R.id.maidplaybtn:
                timeline.setMax(maidAudioPlayer.getDuration());
                maidAudioPlayer.start();
                //antoVideoPlayer.pause();
                //kalimbaAudioPlayer.pause();
                break;

            case R.id.maidpausebtn:

                maidAudioPlayer.pause();
                break;

            case R.id.approfondimentibtn:

               approfondimentiClicked(approfondimentibtn);
                break;
        }



    }

    private Runnable runnable = new Runnable()
    {

        @Override
        public void run( )
        {

            try {
                if (antoVideoPlayer != null) {
                    if (antoVideoPlayer.isPlaying()) {
                        timeline.setProgress(antoVideoPlayer.getCurrentPosition());
                    }
                }
                if (kalimbaAudioPlayer != null) {
                    if (kalimbaAudioPlayer.isPlaying()) {
                        timeline.setProgress(kalimbaAudioPlayer.getCurrentPosition());
                    }
                }
                if (maidAudioPlayer != null) {
                    if (maidAudioPlayer.isPlaying()) {
                        timeline.setProgress(maidAudioPlayer.getCurrentPosition());
                    }
                }
            }catch (Exception ex){}

            handler.postDelayed( this, 500 );
        }
    };

    Handler handler =new Handler()
    {
        @Override
        public void handleMessage(Message mess){

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
