package com.example.dell.itspproject;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.security.Provider;

/**
 * Created by DELL on 20-06-2016.
 */
public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId){
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");

        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                Log.e("Start ID is", state);
                break;
            default:
                startId = 0;
                break;

        }


        if(!this.isRunning && startId==1){
            Log.e("there is no music", "and you want to start");

                media_song = MediaPlayer.create(this, R.raw.tune);
                media_song.setLooping(true);
                media_song.start();
                this.isRunning = true;
                this.isRunning = true;

                  this.startId=0;


        }
        else if(this.isRunning && startId==0){
            Log.e("there is music", "and you want to end");

            media_song.stop();
            media_song.reset();

            this.isRunning=false;
            this.startId=0;
        }
        else if(!this.isRunning && startId ==0){
            Log.e("there is no music", "and you want to end");

            this.isRunning= false;
            this.startId= 0;
        }
        else if(this.isRunning && startId ==1){
            Log.e("there is music", "and you want start");

            this.isRunning= true;
            this.startId= 1;
        }




        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        Log.e("on destroy called", "ta da");

        super.onDestroy();
        this.isRunning=false;
    }

}
