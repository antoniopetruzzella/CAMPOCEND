package com.android.ggallery.museodellemarionette;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Video extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener{

    private MediaPlayer currentMediaPlaying,video1,video2,video3,video4,video5;
    private SurfaceHolder holder;
    private SurfaceView surface;
    private ImageButton gpplaybtn,gppausebtn,palazzobalbibtn,lacollezionebtn,audiostop1,audiostop2,audiostop3,audiostop4,audiostop5;
    private ImageView copertinaprimovideo;
    private SeekBar timeline;
    private TextView ascoltaaudio,video1box,video2box,video3box,video4box,video5box;
    private Intent palazzobalbiIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        surface = (SurfaceView) findViewById(R.id.surfaceview);

        gpplaybtn=(ImageButton)findViewById(R.id.gpplaybtn);
        gppausebtn=(ImageButton)findViewById(R.id.gppausebtn);
        copertinaprimovideo=(ImageView)findViewById(R.id.copertinaprimovideo);
        palazzobalbibtn=(ImageButton)findViewById(R.id.palazzobalbibtn);
        lacollezionebtn=(ImageButton)findViewById(R.id.lacollezionebtn);

        audiostop1=(ImageButton)findViewById(R.id.audiostop1);
        audiostop2=(ImageButton)findViewById(R.id.audiostop2);
        audiostop3=(ImageButton)findViewById(R.id.audiostop3);
        audiostop4=(ImageButton)findViewById(R.id.audiostop4);
        audiostop5=(ImageButton)findViewById(R.id.audiostop5);

        video1box=(TextView)findViewById(R.id.video1box);
        video2box=(TextView)findViewById(R.id.video2box);
        video3box=(TextView)findViewById(R.id.video3box);
        video4box=(TextView)findViewById(R.id.video4box);
        video5box=(TextView)findViewById(R.id.video5box);

        gpplaybtn.setOnClickListener(this);
        gppausebtn.setOnClickListener(this);
        video1box.setOnClickListener(this);
        video2box.setOnClickListener(this);
        video3box.setOnClickListener(this);
        video4box.setOnClickListener(this);
        video5box.setOnClickListener(this);
        audiostop1.setOnClickListener(this);
        audiostop2.setOnClickListener(this);
        audiostop3.setOnClickListener(this);
        audiostop4.setOnClickListener(this);
        audiostop5.setOnClickListener(this);

        palazzobalbibtn.setOnClickListener(this);
        lacollezionebtn.setOnClickListener(this);

        holder = surface.getHolder();
        holder.addCallback(this);
        palazzobalbiIntent=new Intent(this, PalazzoBalbi.class);

        timeline=(SeekBar)findViewById(R.id.timeline);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/mbold.ttf");

        ascoltaaudio=(TextView)findViewById(R.id.ascoltaaudio);

        ascoltaaudio.setTypeface(type);
        Global global=(Global)getApplicationContext();
        global.setInStaticActivity(true);

        Context context = getApplicationContext();
        CharSequence text = "Usa il tasto back del tablet per tornare alla stanza";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        handler.post(runnable);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        video1=MediaPlayer.create(this, R.raw.video1);
        video2=MediaPlayer.create(this, R.raw.video2);
        video3=MediaPlayer.create(this, R.raw.video3);
        video4=MediaPlayer.create(this, R.raw.video4);
        video5=MediaPlayer.create(this, R.raw.video5);

    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onPause(){


        super.onPause();

    }

    @Override
    protected void onStop(){

        super.onStop();
        handler.removeCallbacks(runnable);

        video1.release();
        video2.release();
        video3.release();
        video4.release();
        video5.release();
    }

    @Override
    protected void onResume(){

        super.onResume();
        video1=MediaPlayer.create(this, R.raw.video1);
        video2=MediaPlayer.create(this, R.raw.video2);
        video3=MediaPlayer.create(this, R.raw.video3);
        video4=MediaPlayer.create(this, R.raw.video4);
        video5=MediaPlayer.create(this, R.raw.video5);
        currentMediaPlaying=null;
        Global global=(Global)getApplicationContext();
        global.setInStaticActivity(true);
        handler.post(runnable);

    }

    @Override
    public  void onClick(View view){


        switch (view.getId()){



            case R.id.gppausebtn:
                if(currentMediaPlaying!=null) {
                    currentMediaPlaying.pause();
                }
                break;
            case R.id.gpplaybtn:
                if(currentMediaPlaying!=null) {
                    currentMediaPlaying.start();
                }
                break;
            case R.id.video1box:
            case R.id.audiostop1:
                if(currentMediaPlaying!=null) {
                    currentMediaPlaying.pause();
                }
                video1.setDisplay(null);
                video2.setDisplay(null);
                video3.setDisplay(null);
                video4.setDisplay(null);
                video5.setDisplay(null);

                video1.setDisplay(holder);

                copertinaprimovideo.setVisibility(View.INVISIBLE);
                timeline.setMax((video1.getDuration()));
                video1.start();

                break;
            case R.id.video2box:
            case R.id.audiostop2:
                if(currentMediaPlaying!=null) {
                    currentMediaPlaying.pause();
                }
                video1.setDisplay(null);
                video2.setDisplay(null);
                video3.setDisplay(null);
                video4.setDisplay(null);
                video5.setDisplay(null);

                video2.setDisplay(holder);

                copertinaprimovideo.setVisibility(View.INVISIBLE);
                timeline.setMax((video2.getDuration()));
                video2.start();

                break;
            case R.id.video3box:
            case R.id.audiostop3:
                if(currentMediaPlaying!=null) {
                    currentMediaPlaying.pause();
                }
                video1.setDisplay(null);
                video2.setDisplay(null);
                video3.setDisplay(null);
                video4.setDisplay(null);
                video5.setDisplay(null);

                video3.setDisplay(holder);
                copertinaprimovideo.setVisibility(View.INVISIBLE);
                timeline.setMax((video3.getDuration()));
                video3.start();

                break;
            case R.id.video4box:
            case R.id.audiostop4:
                if(currentMediaPlaying!=null) {
                    currentMediaPlaying.pause();
                }
                video1.setDisplay(null);
                video2.setDisplay(null);
                video3.setDisplay(null);
                video4.setDisplay(null);
                video5.setDisplay(null);
                video4.setDisplay(holder);
                copertinaprimovideo.setVisibility(View.INVISIBLE);
                timeline.setMax((video4.getDuration()));
                video4.start();

                break;
            case R.id.video5box:
            case R.id.audiostop5:
                if(currentMediaPlaying!=null) {
                    currentMediaPlaying.pause();
                }
                video1.setDisplay(null);
                video2.setDisplay(null);
                video3.setDisplay(null);
                video4.setDisplay(null);
                video5.setDisplay(null);
                video5.setDisplay(holder);
                copertinaprimovideo.setVisibility(View.INVISIBLE);
                timeline.setMax((video5.getDuration()));
                video5.start();

                break;
            case R.id.palazzobalbibtn:
                palazzobalbiIntent.putExtra("indirizzo","palazzobalbi.html");
                startActivity(palazzobalbiIntent);
                break;
            case R.id.lacollezionebtn:
                palazzobalbiIntent.putExtra("indirizzo","collezione.html");
                startActivity(palazzobalbiIntent);
                break;




        }



    }

    private Runnable runnable = new Runnable()
    {

        @Override
        public void run( )
        {

            try {
                if (video1 != null) {
                    if (video1.isPlaying()) {
                        currentMediaPlaying=video1;
                        timeline.setProgress(video1.getCurrentPosition());
                    }
                }

                if (video2 != null) {
                    if (video2.isPlaying()) {
                        currentMediaPlaying=video2;
                        timeline.setProgress(video2.getCurrentPosition());
                    }
                }

                if (video3 != null) {
                    if (video3.isPlaying()) {
                        currentMediaPlaying=video3;
                        timeline.setProgress(video3.getCurrentPosition());
                    }
                }

                if (video4 != null) {
                    if (video4.isPlaying()) {
                        currentMediaPlaying=video4;
                        timeline.setProgress(video4.getCurrentPosition());
                    }
                }
                if (video5 != null) {
                    if (video5.isPlaying()) {
                        currentMediaPlaying=video5;
                        timeline.setProgress(video5.getCurrentPosition());
                    }
                }

            }catch (Exception ex){}

            handler.postDelayed( this, 50);
        }
    };

    Handler handler =new Handler()
    {
        @Override
        public void handleMessage(Message mess){

        }

    };
}
